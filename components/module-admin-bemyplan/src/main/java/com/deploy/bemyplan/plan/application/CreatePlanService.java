package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.Location;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.image.s3.S3Locator;
import com.deploy.bemyplan.plan.PreviewAdapter;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.PlanDto;
import com.deploy.bemyplan.plan.application.port.in.PreviewDto;
import com.deploy.bemyplan.plan.application.port.in.SpotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class CreatePlanService implements CreatePlanUseCase {
    private final SpotRepository spotRepository;
    private final PreviewAdapter previewAdapter;
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Transactional
    public void createPlan(CreatePlanRequest request) {
        final Plan plan = createNewPlan(request);

        final List<SpotDto> spotDtos = request.getSpots();
        final List<Spot> spots = createSpotsBySchedules(plan, spotDtos);
        createPreviews(request.getPreviews(), plan, spots);
    }


    private void createPreviews(List<PreviewDto> previewDtos, Plan plan, List<Spot> spots) {
        previewAdapter.saveAll(previewDtos, plan, spots);
    }

    private List<Spot> createSpotsBySchedules(Plan plan, List<SpotDto> spotDtos) {
        final List<Spot> spots = spotDtos.stream()
                .map(spotDto ->
                        new Spot(spotDto.getName(),
                                spotDto.getType(),
                                Location.of(spotDto.getAddress(), spotDto.getLatitude(), spotDto.getLongitude()),
                                spotDto.getTip(),
                                spotDto.getReview(),
                                plan,
                                spotDto.getDate(),
                                spotDto.getVehicle(),
                                spotDto.getSpentTime()))
                .collect(Collectors.toList());
        spotRepository.saveAll(spots);

        for (int i = 0; i < spotDtos.size(); i++) {
            SpotDto spotDto = spotDtos.get(i);
            Spot spot = spots.get(i);

            spot.setImage(spotDto.getSavedImages()
                    .stream()
                    .map(image -> new SpotImage(S3Locator.get(image), spot))
                    .collect(Collectors.toList()));
        }

        return spots;
    }

    private Plan createNewPlan(CreatePlanRequest request) {
        final int totalDays = request.getSpots().stream()
                .mapToInt(SpotDto::getDate)
                .boxed()
                .collect(Collectors.toSet())
                .size();

        final PlanDto planDto = request.getPlan();
        final Plan plan = planMapper.toDomain(planDto, totalDays);
        planRepository.save(plan);
        return plan;
    }
}
