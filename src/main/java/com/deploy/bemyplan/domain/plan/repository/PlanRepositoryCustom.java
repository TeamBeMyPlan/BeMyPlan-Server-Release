package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanRepositoryCustom {

    List<Plan> findAllByIds(List<Long> planIds);
    List<Plan> findPlansUsingCursor(int size, Long lastPlanId, Pageable pageable, RcmndStatus rcmndStatus);
}