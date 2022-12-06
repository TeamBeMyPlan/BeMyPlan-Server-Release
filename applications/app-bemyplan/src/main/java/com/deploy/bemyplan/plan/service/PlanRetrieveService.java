package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.plan.service.dto.request.RetrieveMyBookmarkListRequestDto;
import com.deploy.bemyplan.plan.service.dto.request.RetrieveMyOrderListRequestDto;
import com.deploy.bemyplan.plan.service.dto.response.OrdersScrollResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanInfoResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanListResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponse;
import com.deploy.bemyplan.plan.service.dto.response.ScrapsScrollResponse;
import com.deploy.bemyplan.plan.service.dto.response.SpotMoveInfoResponse;
import com.deploy.bemyplan.user.service.UserServiceUtils;
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

    public PlanListResponse retrievePlans(final Long userId, final RegionCategory region) {
        final List<Plan> planList = planRepository.findAllPlanByRegionCategory(region);
        return getPlanListWithPersonalStatus(planList, userId);
    }

    public PlanListResponse getPickList(final Long userId) {
        final List<Plan> planList = planRepository.findPickList();
        return getPlanListWithPersonalStatus(planList, userId);
    }

    public PlanPreviewResponse getPreviewPlanInfo(final Long planId) {
        final Plan plan = PlanServiceUtils.findPlanByIdFetchJoinSchedule(planRepository, planId);
        final User user = userRepository.findUserById(plan.getUserId());
        final List<PreviewContent> previewContents = planRepository.findPreviewContentsByPlanId(plan.getId());

        return PlanPreviewResponse.of(plan, user.getNickname(), previewContents);
    }

    public PlanDetailResponse getPlanDetailInfo(final Long planId) {
        final Plan plan = PlanServiceUtils.findPlanByIdFetchJoinSchedule(planRepository, planId);
        final User user = UserServiceUtils.findUserById(userRepository, plan.getUserId());

        return PlanDetailResponse.of(plan, user);
    }

    public List<SpotMoveInfoResponse> getSpotMoveInfos(final Long planId) {
        final Plan plan = PlanServiceUtils.findPlanById(planRepository, planId);

        return plan.getSchedules().stream()
                .map(SpotMoveInfoResponse::of)
                .collect(Collectors.toList());
    }

    public ScrapsScrollResponse retrieveMyBookmarkList(final RetrieveMyBookmarkListRequestDto request, final Long userId, final Pageable pageable) {
        final List<Plan> planWithNextCursor = planRepository.findMyBookmarkListUsingCursor(userId, pageable, request.getSize() + 1, request.getLastScrapId());
        final ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, request.getSize());

        final AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        final ScrapDictionary scrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        final OrderDictionary orderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        if (plansCursor.isLastScroll()) {
            return ScrapsScrollResponse.newLastCursor(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors);
        }
        final Scrap nextCursor = scrapRepository.findActiveByUserIdAndPlanId(plansCursor.getNextCursor().getId(), userId);
        return ScrapsScrollResponse.newCursorHasNext(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors, nextCursor.getId());
    }

    public OrdersScrollResponse retrieveMyOrderList(final RetrieveMyOrderListRequestDto request, final Long userId, final Pageable pageable) {
        final List<Plan> planWithNextCursor = planRepository.findMyOrderListUsingCursor(userId, pageable, request.getSize() + 1, request.getLastOrderId());
        final ScrollPaginationCollection<Plan> plansCursor = ScrollPaginationCollection.of(planWithNextCursor, request.getSize());

        final AuthorDictionary authors = AuthorDictionary.of(planWithNextCursor, userRepository);

        final ScrapDictionary scrapDictionary = findScrapByUserIdAndPlans(userId, planWithNextCursor);
        final OrderDictionary orderDictionary = findOrderByUserIdAndPlans(userId, planWithNextCursor);

        if (plansCursor.isLastScroll()) {
            return OrdersScrollResponse.newLastCursor(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors);
        }
        final Order nextCursor = orderRepository.findByPlanIdAndUserId(plansCursor.getNextCursor().getId(), userId).get();
        return OrdersScrollResponse.newCursorHasNext(plansCursor.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors, nextCursor.getId());
    }

    private PlanListResponse getPlanListWithPersonalStatus(final List<Plan> planList, final Long userId) {
        return PlanListResponse.of(planList.stream()
                .map(plan -> PlanInfoResponse.of(plan,
                        getAuthorByPlanId(plan),
                        isScraped(userId, plan),
                        isOrdered(userId, plan)))
                .collect(Collectors.toList()));
    }

    private boolean isScraped(final Long userId, final Plan plan) {
        return userId != null && scrapRepository.existsScrapByUserIdAndPlanId(userId, plan.getId());
    }

    private boolean isOrdered(final Long userId, final Plan plan) {
        return userId != null && orderRepository.existsOrderByUserIdAndPlanIdAndStatus(userId, plan.getId(), OrderStatus.COMPLETED);
    }

    private User getAuthorByPlanId(final Plan plan) {
        return userRepository.findUserByPlanId(plan.getId())
                .orElseThrow(() -> new NotFoundException("크리에이터 정보가 존재하지 않습니다."));
    }

    private ScrapDictionary findScrapByUserIdAndPlans(final Long userId, final List<Plan> plans) {
        final List<Long> planIds = plans.stream()
                .map(Plan::getId)
                .collect(Collectors.toList());
        return ScrapDictionary.of(scrapRepository.findByUserIdAndPlanIds(planIds, userId));
    }

    private OrderDictionary findOrderByUserIdAndPlans(final Long userId, final List<Plan> plans) {
        final List<Long> planIds = plans.stream()
                .map(Plan::getId)
                .collect(Collectors.toList());
        return OrderDictionary.of(orderRepository.findByUserIdAndPlanIds(planIds, userId));
    }
}