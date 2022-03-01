package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanRepositoryCustom {

    List<Plan> findAllByIds(List<Long> planIds);
    List<Plan> findPlansUsingCursor(int size, Long lastPlanId, Pageable pageable, RegionType region, RcmndStatus rcmndStatus);
}