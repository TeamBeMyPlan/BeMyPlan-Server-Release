package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.domain.collection.AuthorDictionary;
import com.deploy.bemyplan.domain.common.collection.ScrollPaginationCollection;
import com.deploy.bemyplan.domain.plan.*;
import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyPlanBookmarksRequestDto;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyPlansOrdered;
import com.deploy.bemyplan.service.plan.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanInfoResponse;
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
import org.springframework.data.domain.Page;
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
        List<Plan> planWithNextCursor = planRepository.findPlansUsingCursor(size+1, lastPlanId, pageable, region, rcmndStatus);
        ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, size);

        AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        UserScrapDictionary userScrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        UserOrderDictionary userOrderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        return PlansScrollResponse.of(plansCursor, userScrapDictionary, userOrderDictionary, authors);
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

    @Transactional(readOnly = true)
    public PlansScrollResponse retrieveMyPlanBookmarks(RetrieveMyPlanBookmarksRequestDto request, Long userId, Pageable pageable) {
        List<Plan> planWithNextCursor = planRepository.findMyPlanBookmarksUsingCursor(userId, pageable, request.getSize()+1, request.getLastScrapId());
        ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, request.getSize());

        AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        UserScrapDictionary userScrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        UserOrderDictionary userOrderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        return PlansScrollResponse.of(plansCursor, userScrapDictionary, userOrderDictionary, authors);
    }

    @Transactional(readOnly = true)
    public PlansScrollResponse retrieveMyPlansOrdered(RetrieveMyPlansOrdered request, Long userId, Pageable pageable) {
        List<Plan> planWithNextCursor = planRepository.findMyPlansOrderedUsingCursor(userId, pageable, request.getSize() + 1, request.getLastOrderId());
        ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, request.getSize());

        AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        UserScrapDictionary userScrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        UserOrderDictionary userOrderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        return PlansScrollResponse.of(plansCursor, userScrapDictionary, userOrderDictionary, authors);
    }
}