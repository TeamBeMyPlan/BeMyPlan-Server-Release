package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.Location;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.image.s3.S3Locator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreatePlanService {
    private final SpotRepository spotRepository;
    private final PreviewAdapter previewAdapter;
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    private final CreatorRepository creatorRepository;

    @Transactional
    public void createPlan(CreatePlanRequest request) {
        final Plan plan = createNewPlan(request);

        final List<SpotDto> spotDtos = request.getSpots();
        final List<Spot> spots = createSpotsBySchedules(plan, spotDtos);
        createPreviews(request.getPreviews(), plan, spots);
    }

    public List<Creator> getCreators() {
        return creatorRepository.findAll();
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
