package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.plan.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanRepositoryCustom {

    List<Plan> findAllByIds(List<Long> planIds);
    Plan findPlanById(Long id);
    Plan findPlanByIdFetchJoinSchedule(Long id);
    List<Plan> findMyPlanBookmarksUsingCursor(Long userId, @Nullable Pageable pageable, int size, @Nullable Long lastScrapId);
    List<Plan> findPlansUsingCursor(int size, Long lastPlanId, Pageable pageable, RegionType region, RcmndStatus rcmndStatus);
    List<PreviewContent> findPreviewContentsByPlanId(Long planId);
    List<DailySchedule> findSchedulesByPlanId(Long planId);
}