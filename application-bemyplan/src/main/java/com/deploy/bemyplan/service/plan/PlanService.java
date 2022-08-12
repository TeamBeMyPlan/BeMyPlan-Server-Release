package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_PLAN_EXCEPTION;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final PreviewRepository previewRepository;

    @Transactional(readOnly = true)
    public PlanPreviewResponseDto getPlanPreview(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 일정 (%s) 입니다", planId), NOT_FOUND_PLAN_EXCEPTION));
        List<Preview> previews = previewRepository.findAllPreviewByPlanId(planId);

        final int spotCount = getSpotCount(plan);
        final int restaurantCount = getRestaurantCount(plan);

        return PlanPreviewResponseDto.of(plan, previews, spotCount, restaurantCount);
    }

    private int getRestaurantCount(final Plan plan) {
        return (int) plan.getSchedules().stream()
                .flatMap(dailySchedule -> dailySchedule.getSpots().stream())
                .filter(spot -> SpotCategoryType.RESTAURANT == spot.getCategory())
                .count();
    }

    private int getSpotCount(final Plan plan) {
        return (int) plan.getSchedules().stream()
                .mapToLong(dailySchedule -> (long) dailySchedule.getSpots().size())
                .sum();
    }
}
