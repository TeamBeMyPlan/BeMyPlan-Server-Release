package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanRepositoryCustom {

    List<Plan> findMyBookmarkListUsingCursor(Long userId, @Nullable Pageable pageable, int size, @Nullable Long lastScrapId);

    List<Plan> findMyOrderListUsingCursor(Long userId, @Nullable Pageable pageable, int size, @Nullable Long lastOrderId);

    List<PreviewContent> findPreviewContentsByPlanId(Long planId);
}