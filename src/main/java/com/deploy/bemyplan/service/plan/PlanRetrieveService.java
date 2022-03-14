package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.domain.common.collection.ScrollPaginationCollection;
import com.deploy.bemyplan.domain.plan.*;
import com.deploy.bemyplan.service.plan.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlansScrollResponse;
import com.deploy.bemyplan.domain.collection.UserOrderDictionary;
import com.deploy.bemyplan.domain.collection.UserScrapDictionary;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanRetrieveService {

    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final ScrapRepository scrapRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public PlansScrollResponse retrievePlans(Long userId, int size, Long lastPlanId, Pageable pageable, RegionType region, RcmndStatus rcmndStatus) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        List<Plan> planWithNextCursor = planRepository.findPlansUsingCursor(size+1, lastPlanId, pageable, region, rcmndStatus);
        ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, size);

        UserScrapDictionary userScrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        UserOrderDictionary userOrderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        return PlansScrollResponse.of(plansCursor, userScrapDictionary, userOrderDictionary, user);
    }

    private UserScrapDictionary findScrapByUserIdAndPlans(Long userId, List<Plan> plans) {
        List<Long> planIds = plans.stream()
                .map(Plan::getId)
                .collect(Collectors.toList());
        return UserScrapDictionary.of(scrapRepository.findByUserIdAndPlanIds(planIds, userId));
    }

    private UserOrderDictionary findOrderByUserIdAndPlans(Long userId, List<Plan> plans) {
        List<Long> planIds = plans.stream()
                .map(Plan::getId)
                .collect(Collectors.toList());
        return UserOrderDictionary.of(orderRepository.findByUserIdAndPlanIds(planIds, userId));
    }

    @Transactional(readOnly = true)
    public PlanPreviewResponse getPreviewPlanInfo(Long planId) {
        Plan plan = PlanServiceUtils.findPlanByIdFetchJoinSchedule(planRepository, planId);
        List<PreviewContent> previewContents = planRepository.findPreviewContentsByPlanId(plan.getId());

        return PlanPreviewResponse.of(plan, previewContents);
    }

    @Transactional(readOnly = true)
    public PlanDetailResponse getPlanDetailInfo(Long planId) {
        Plan plan = PlanServiceUtils.findPlanByIdFetchJoinSchedule(planRepository, planId);
        User user = UserServiceUtils.findUserById(userRepository, plan.getUserId());
        List<DailySchedule> schedules = planRepository.findSchedulesByPlanId(planId);
        return PlanDetailResponse.of(plan, user, schedules);
    }
}