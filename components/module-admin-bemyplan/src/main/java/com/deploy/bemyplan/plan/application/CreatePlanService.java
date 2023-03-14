package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.Location;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.image.s3.S3Locator;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanDto;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.CreatePreviewDto;
import com.deploy.bemyplan.plan.application.port.in.CreateSpotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class CreatePlanService implements CreatePlanUseCase {
    private final SpotRepository spotRepository;
    private final PreviewRepository previewRepository;
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Transactional
    public void createPlan(CreatePlanRequest request) {
        final Plan plan = createNewPlan(request);

        final List<CreateSpotDto> createSpotDtos = request.getSpots();
        final List<Spot> spots = createSpotsBySchedules(plan, createSpotDtos);
        createPreviews(request.getPreviews(), plan, spots);
    }


    private void createPreviews(List<CreatePreviewDto> createPreviewDtos, Plan plan, List<Spot> spots) {
        final List<Preview> previews = createPreviewDtos.stream()
                .map(preview -> new Preview(
                        plan,
                        preview.getDescription(),
                        spots.get(preview.getSpotSeq())))
                .collect(Collectors.toList());
        previewRepository.saveAll(previews);
    }

    private List<Spot> createSpotsBySchedules(Plan plan, List<CreateSpotDto> createSpotDtos) {
        final List<Spot> spots = createSpotDtos.stream()
                .map(spotDto ->
                        new Spot(spotDto.getName(),
                                spotDto.getType(),
                                Location.of(spotDto.getAddress(), spotDto.getLatitude(), spotDto.getLongitude()),
                                spotDto.getTip(),
                                spotDto.getReview(),
                                spotDto.getSavedImages().stream().map(S3Locator::get).collect(Collectors.toList()),
                                plan,
                                spotDto.getDate(),
                                spotDto.getVehicle(),
                                spotDto.getSpentTime()))
                .collect(Collectors.toList());
        return spotRepository.saveAll(spots);
    }

    private Plan createNewPlan(CreatePlanRequest request) {
        final int totalDays = request.getSpots().stream()
                .mapToInt(CreateSpotDto::getDate)
                .boxed()
                .collect(Collectors.toSet())
                .size();

        final CreatePlanDto createPlanDto = request.getPlan();
        final Plan plan = planMapper.toDomain(createPlanDto, totalDays);
        planRepository.save(plan);
        return plan;
    }
}
