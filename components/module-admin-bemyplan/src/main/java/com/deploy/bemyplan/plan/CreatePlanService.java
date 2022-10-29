package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.common.Location;
import com.deploy.bemyplan.domain.plan.DailySchedule;
import com.deploy.bemyplan.domain.plan.DailyScheduleRepository;
import com.deploy.bemyplan.domain.plan.JsonValueType;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.SpotMoveInfo;
import com.deploy.bemyplan.domain.plan.SpotMoveInfoRepository;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.image.s3.S3Locator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreatePlanService {
    private final SpotRepository spotRepository;
    private final DailyScheduleRepository scheduleRepository;
    private final SpotMoveInfoRepository moveInfoRepository;
    private final PreviewAdapter previewAdapter;
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Transactional
    public void createPlan(CreatePlanRequest request) {
        final Plan plan = createNewPlan(request);

        final List<SpotDto> spotDtos = request.getSpots();
        final List<DailySchedule> dailySchedules = createSchedulesByPlan(plan, spotDtos);

        final List<Spot> spots = createSpotsBySchedules(spotDtos, dailySchedules);

        createMoveInfoBySchedules(spotDtos, dailySchedules, spots);

        final List<PreviewDto> previews = request.getPreviews();
        createPreviews(previews, plan, spots);
    }

    private void createPreviews(List<PreviewDto> previewDtos, Plan plan, List<Spot> spots) {
        List<Preview> previews = previewDtos.stream()
                .map(preview -> Preview.newInstance(plan, List.of(S3Locator.get(preview.getImage())),
                        preview.getDescription(),
                        PreviewContentStatus.ACTIVE,
                        spots.get(preview.getSpotId()).getId()))
                .collect(Collectors.toList());

        List<PreviewContent> legacyPreviews = new ArrayList<>();
        previewDtos.forEach(preview -> {
                    legacyPreviews.add(new PreviewContent(plan, JsonValueType.IMAGE, S3Locator.get(preview.getImage())));
                    legacyPreviews.add(new PreviewContent(plan, JsonValueType.TEXT, preview.getDescription()));
                });

        previewAdapter.saveAll(previews);
        previewAdapter.saveLegacyAll(legacyPreviews);
    }

    private void createMoveInfoBySchedules(List<SpotDto> spotDtos, List<DailySchedule> dailySchedules, List<Spot> spots) {
        final List<SpotMoveInfo> moveInfos = new ArrayList<>();
        for (int i = 0; i < spotDtos.size(); i++) {
            final SpotDto spotDto = spotDtos.get(i);

            if (spotDto.hasNext()) {
                final SpotDto.NextSpot nextSpot = spotDto.getNextSpot();
                final Spot current = spots.get(spotDto.getId());
                final Spot next = spots.get(nextSpot.getId());
                final SpotMoveInfo moveInfo = new SpotMoveInfo(current.getId(), next.getId(), nextSpot.getVehicle(), nextSpot.getSpentTime(),
                        getSchedule(dailySchedules, spotDto.getDate()));
                moveInfos.add(moveInfo);
            }
        }

        moveInfoRepository.saveAll(moveInfos);
    }

    private List<Spot> createSpotsBySchedules(List<SpotDto> spotDtos, List<DailySchedule> dailySchedules) {
        final List<Spot> spots = spotDtos.stream()
                .map(spotDto ->
                        new Spot(spotDto.getName(),
                                spotDto.getType(),
                                Location.of(spotDto.getLatitude(), spotDto.getLongitude()),
                                spotDto.getTip(),
                                spotDto.getReview(),
                                getSchedule(dailySchedules, spotDto.getDate())))
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

    private List<DailySchedule> createSchedulesByPlan(Plan plan, List<SpotDto> spotDtos) {
        final Set<Integer> scheduleDays = spotDtos.stream()
                .mapToInt(SpotDto::getDate)
                .boxed()
                .collect(Collectors.toSet());

        List<DailySchedule> dailySchedules = scheduleDays.stream()
                .map(day -> new DailySchedule(plan, day))
                .collect(Collectors.toList());

        scheduleRepository.saveAll(dailySchedules);
        return dailySchedules;
    }

    private Plan createNewPlan(CreatePlanRequest request) {
        final PlanDto planDto = request.getPlan();
        final Plan plan = planMapper.toDomain(planDto);
        planRepository.save(plan);
        return plan;
    }

    private DailySchedule getSchedule(List<DailySchedule> dailySchedules, int day) {
        return dailySchedules.stream()
                .filter(schedule -> day == schedule.getDay())
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
