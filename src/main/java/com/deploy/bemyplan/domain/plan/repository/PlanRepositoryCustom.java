package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.plan.Plan;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanRepositoryCustom {

    List<Plan> findAllByIds(List<Long> planIds);
    List<Plan> findPopularsUsingCursor(int size, Long lastPlanId, Pageable pageable);
}