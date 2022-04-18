package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.domain.collection.AuthorDictionary;
import com.deploy.bemyplan.domain.common.collection.ScrollPaginationCollection;
import com.deploy.bemyplan.domain.plan.*;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyBookmarkListRequestDto;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyOrderListRequestDto;
import com.deploy.bemyplan.service.plan.dto.request.RetrievePickListRequestDto;
import com.deploy.bemyplan.service.plan.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlansScrollResponse;
import com.deploy.bemyplan.domain.collection.OrderDictionary;
import com.deploy.bemyplan.domain.collection.ScrapDictionary;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.service.plan.dto.response.SpotMoveInfoResponse;
import com.deploy.bemyplan.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlanRetrieveService {

    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final ScrapRepository scrapRepository;
    private final OrderRepository orderRepository;

    public PlansScrollResponse retrievePlans(Long userId, int size, Long lastPlanId, Pageable pageable, RegionType region) {
        List<Plan> planWithNextCursor = planRepository.findPlansUsingCursor(size + 1, lastPlanId, pageable, region);
        return getPlanListWithPersonalStatusUsingCursor(planWithNextCursor, userId, size);
    }

    public PlansScrollResponse getPickList(RetrievePickListRequestDto request, Long userId) {
        List<Plan> planWithNextCursor = planRepository.findPickListUsingCursor(request.getSize() + 1, request.getLastPlanId());
        return getPlanListWithPersonalStatusUsingCursor(planWithNextCursor, userId, request.getSize());
    }

    public PlanPreviewResponse getPreviewPlanInfo(Long planId) {
        Plan plan = PlanServiceUtils.findPlanByIdFetchJoinSchedule(planRepository, planId);
        List<PreviewContent> previewContents = planRepository.findPreviewContentsByPlanId(plan.getId());

        return PlanPreviewResponse.of(plan, previewContents);
    }

    public PlanDetailResponse getPlanDetailInfo(Long planId) {
        Plan plan = PlanServiceUtils.findPlanByIdFetchJoinSchedule(planRepository, planId);
        User user = UserServiceUtils.findUserById(userRepository, plan.getUserId());

        return PlanDetailResponse.of(plan, user);
    }

    public List<SpotMoveInfoResponse> getSpotMoveInfos(Long planId) {
        Plan plan = PlanServiceUtils.findPlanById(planRepository, planId);

        return plan.getSchedules().stream()
                .map(SpotMoveInfoResponse::of)
                .collect(Collectors.toList());
    }

    public PlansScrollResponse retrieveMyBookmarkList(RetrieveMyBookmarkListRequestDto request, Long userId, Pageable pageable) {
        List<Plan> planWithNextCursor = planRepository.findMyBookmarkListUsingCursor(userId, pageable, request.getSize() + 1, request.getLastScrapId());
        return getPlanListWithPersonalStatusUsingCursor(planWithNextCursor, userId, request.getSize());
    }

    public PlansScrollResponse retrieveMyOrderList(RetrieveMyOrderListRequestDto request, Long userId, Pageable pageable) {
        List<Plan> planWithNextCursor = planRepository.findMyOrderListUsingCursor(userId, pageable, request.getSize() + 1, request.getLastOrderId());
        return getPlanListWithPersonalStatusUsingCursor(planWithNextCursor, userId, request.getSize());
    }

    private PlansScrollResponse getPlanListWithPersonalStatusUsingCursor(List<Plan> planWithNextCursor, Long userId, int size) {
        ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, size);

        AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        ScrapDictionary scrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        OrderDictionary orderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);
        return PlansScrollResponse.of(plansCursor, scrapDictionary, orderDictionary, authors);
    }

    private ScrapDictionary findScrapByUserIdAndPlans(Long userId, List<Plan> plans) {
        List<Long> planIds = plans.stream()
                .map(Plan::getId)
                .collect(Collectors.toList());
        return ScrapDictionary.of(scrapRepository.findByUserIdAndPlanIds(planIds, userId));
    }

    private OrderDictionary findOrderByUserIdAndPlans(Long userId, List<Plan> plans) {
        List<Long> planIds = plans.stream()
                .map(Plan::getId)
                .collect(Collectors.toList());
        return OrderDictionary.of(orderRepository.findByUserIdAndPlanIds(planIds, userId));
    }
}