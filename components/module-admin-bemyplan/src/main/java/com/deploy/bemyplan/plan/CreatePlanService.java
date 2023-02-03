package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.DailySchedule;
import com.deploy.bemyplan.domain.plan.DailyScheduleRepository;
import com.deploy.bemyplan.domain.plan.Location;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.SpotMoveInfo;
import com.deploy.bemyplan.domain.plan.SpotMoveInfoRepository;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.image.s3.S3Locator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
public class CreatePlanService {
    private final SpotRepository spotRepository;
    private final DailyScheduleRepository scheduleRepository;
    private final SpotMoveInfoRepository moveInfoRepository;
    private final PreviewAdapter previewAdapter;
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    private final CreatorRepository creatorRepository;

    @Transactional
    public void createPlan(CreatePlanRequest request) {
        final Plan plan = createNewPlan(request);

        final List<SpotDto> spotDtos = request.getSpots();
        final List<DailySchedule> dailySchedules = createSchedulesByPlan(plan, spotDtos);

        final List<Spot> spots = createSpotsBySchedules(spotDtos, dailySchedules);
        createMoveInfoBySchedules(spotDtos, dailySchedules, spots);

        createPreviews(request.getPreviews(), plan, spots);
    }

    public List<Creator> getCreators() {
        return creatorRepository.findAll();
    }

    private void createPreviews(List<PreviewDto> previewDtos, Plan plan, List<Spot> spots) {
        previewAdapter.saveAll(previewDtos, plan, spots);
    }

    private void createMoveInfoBySchedules(List<SpotDto> spotDtos, List<DailySchedule> dailySchedules, List<Spot> spots) {
        final List<SpotMoveInfo> moveInfos = new ArrayList<>();

        final Map<Integer, List<SpotDto>> spotsPerDate = spotDtos.stream().collect(groupingBy((SpotDto::getDate)));
        for (Integer date : spotsPerDate.keySet()) {
            List<SpotDto> dateSpots = spotsPerDate.get(date);

            for (int i = 0; i < dateSpots.size(); i++) {
                if (i == dateSpots.size() - 1) {
                    break;
                }

                final SpotDto spotDto = dateSpots.get(i);

                final Spot current = spots.get(spotDto.getSeq());
                final Spot next = spots.get(dateSpots.get(i + 1).getSeq());

                final SpotMoveInfo moveInfo = new SpotMoveInfo(current.getId(), next.getId(), spotDto.getVehicle(), spotDto.getSpentTime(),
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
                                Location.of(spotDto.getAddress(), spotDto.getLatitude(), spotDto.getLongitude()),
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
        final int totalDays = request.getSpots().stream()
                .mapToInt(SpotDto::getDate)
                .boxed()
                .collect(Collectors.toSet())
                .size();

        final PlanDto planDto = request.getPlan();
        final Plan plan = planMapper.toDomain(planDto, request.getCreator(), totalDays);
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
