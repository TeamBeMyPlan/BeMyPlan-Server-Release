package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.collection.AuthorDictionary;
import com.deploy.bemyplan.domain.collection.OrderDictionary;
import com.deploy.bemyplan.domain.collection.ScrapDictionary;
import com.deploy.bemyplan.domain.plan.Plan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersScrollResponse {

    private static final long LAST_CURSOR = -1L;

    private List<PlanInfoResponse> contents = new ArrayList<>();
    private long nextCursor;

    private OrdersScrollResponse(List<PlanInfoResponse> contents, long nextCursor) {
        this.contents = contents;
        this.nextCursor = nextCursor;
    }

//    public static OrdersScrollResponse of(ScrollPaginationCollection<Plan> plansScroll, ScrapDictionary scrapDictionary, OrderDictionary orderDictionary, AuthorDictionary authors) {
//        if (plansScroll.isLastScroll()) {
//            return newLastCursor(plansScroll.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors);
//        }
//        return newCursorHasNext(plansScroll.getCurrentScrollItems(), scrapDictionary, orderDictionary, authors, plansScroll.getNextCursor().getId());
//    }

    public static OrdersScrollResponse newLastCursor(List<Plan> plans, ScrapDictionary scrapDictionary, OrderDictionary orderDictionary, AuthorDictionary authors) {
        return newCursorHasNext(plans, scrapDictionary, orderDictionary, authors, LAST_CURSOR);
    }

    public static OrdersScrollResponse newCursorHasNext(List<Plan> plans, ScrapDictionary scrapDictionary, OrderDictionary orderDictionary, AuthorDictionary authors, long nextCursor) {
        List<PlanInfoResponse> contents = plans.stream()
                .map(plan -> PlanInfoResponse.of(plan, authors.getAuthorByPlanId(plan.getId()),
                        getScarpStatus(plan, scrapDictionary), getOrderStatus(plan, orderDictionary)))
                .collect(Collectors.toList());
        return new OrdersScrollResponse(contents, nextCursor);
    }

    private static boolean getScarpStatus(Plan plan, ScrapDictionary scrapDictionary) {
        return scrapDictionary.existByPlanId(plan.getId());
    }

    private static boolean getOrderStatus(Plan plan, OrderDictionary orderDictionary) {
        return orderDictionary.existByPlanId(plan.getId());
    }
}