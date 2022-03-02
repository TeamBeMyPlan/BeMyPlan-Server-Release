package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_PLAN_EXCEPTION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanServiceUtils {

    @NotNull
    public static Plan findPlanById(PlanRepository planRepository, Long planId) {
        Plan plan = planRepository.findPlanById(planId);
        if (Objects.isNull(plan)) {
            throw new NotFoundException(String.format("존재하지 않는 일정 (%s) 입니다", planId), NOT_FOUND_PLAN_EXCEPTION);
        }
        return plan;
    }

    @NotNull
    public static Plan findPlanByIdFetchJoinSchedule(PlanRepository planRepository, Long planId) {
        Plan plan = planRepository.findPlanByIdFetchJoinSchedule(planId);
        if (Objects.isNull(plan)) {
            throw new NotFoundException(String.format("존재하지 않는 일정 (%s) 입니다", planId), NOT_FOUND_PLAN_EXCEPTION);
        }
        return plan;
    }
}
