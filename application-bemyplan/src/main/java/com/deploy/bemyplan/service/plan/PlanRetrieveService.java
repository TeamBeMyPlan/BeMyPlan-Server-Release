package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.domain.common.collection.ScrollPaginationCollection;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.service.collection.AuthorDictionary;
import com.deploy.bemyplan.service.collection.OrderDictionary;
import com.deploy.bemyplan.service.collection.ScrapDictionary;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyBookmarkListRequestDto;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyOrderListRequestDto;
import com.deploy.bemyplan.service.plan.dto.request.RetrievePickListRequestDto;
import com.deploy.bemyplan.service.plan.dto.response.OrdersScrollResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlansScrollResponse;
import com.deploy.bemyplan.service.plan.dto.response.ScrapsScrollResponse;
import com.deploy.bemyplan.service.plan.dto.response.SpotMoveInfoResponse;
import com.deploy.bemyplan.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public PlansScrollResponse retrievePlans(Long userId, int size, Long authorId, Long lastPlanId, Pageable pageable, RegionCategory region) {
        List<Plan> planWithNextCursor = planRepository.findPlansUsingCursor(size + 1, authorId, lastPlanId, pageable, region);
        return getPlanListWithPersonalStatusUsingCursor(planWithNextCursor, userId, size);
    }

    public PlansScrollResponse getPickList(RetrievePickListRequestDto request, Long userId) {
        List<Plan> planWithNextCursor = planRepository.findPickListUsingCursor(request.getSize() + 1, request.getLastPlanId());
        return getPlanListWithPersonalStatusUsingCursor(planWithNextCursor, userId, request.getSize());
    }

    public PlanPreviewResponse getPreviewPlanInfo(Long planId) {
        Plan plan = PlanServiceUtils.findPlanByIdFetchJoinSchedule(planRepository, planId);
        User user = userRepository.findUserById(plan.getUserId());
        List<PreviewContent> previewContents = planRepository.findPreviewContentsByPlanId(plan.getId());

        return PlanPreviewResponse.of(plan, user.getNickname(), previewContents);
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

    public ScrapsScrollResponse retrieveMyBookmarkList(RetrieveMyBookmarkListRequestDto request, Long userId, Pageable pageable) {
        List<Plan> planWithNextCursor = planRepository.findMyBookmarkListUsingCursor(userId, pageable, request.getSize() + 1, request.getLastScrapId());
        ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, request.getSize());

        AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        ScrapDictionary scrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        OrderDictionary orderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        if (plansCursor.isLastScroll()) {
            return ScrapsScrollResponse.newLastCursor(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors);
        }
        Scrap nextCursor = scrapRepository.findActiveByUserIdAndPlanId(plansCursor.getNextCursor().getId(), userId);
        return ScrapsScrollResponse.newCursorHasNext(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors, nextCursor.getId());
    }

    public OrdersScrollResponse retrieveMyOrderList(RetrieveMyOrderListRequestDto request, Long userId, Pageable pageable) {
        List<Plan> planWithNextCursor = planRepository.findMyOrderListUsingCursor(userId, pageable, request.getSize() + 1, request.getLastOrderId());
        ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, request.getSize());

        AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        ScrapDictionary scrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        OrderDictionary orderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        if (plansCursor.isLastScroll()) {
            return OrdersScrollResponse.newLastCursor(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors);
        }
        Order nextCursor = orderRepository.findByUserIdAndPlanId(plansCursor.getNextCursor().getId(), userId);
        return OrdersScrollResponse.newCursorHasNext(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors, nextCursor.getId());
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