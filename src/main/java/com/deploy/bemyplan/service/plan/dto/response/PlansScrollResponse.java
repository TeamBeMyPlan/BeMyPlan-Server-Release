package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.common.collection.ScrollPaginationCollection;
import com.deploy.bemyplan.domain.collection.UserOrderDictionary;
import com.deploy.bemyplan.domain.collection.UserScrapDictionary;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlansScrollResponse {

    private static final long LAST_CURSOR = -1L;

    private List<PlanInfoResponse> contents = new ArrayList<>();
    private long nextCursor;

    private PlansScrollResponse(List<PlanInfoResponse> contents, long nextCursor) {
        this.contents = contents;
        this.nextCursor = nextCursor;
    }

    public static PlansScrollResponse of(ScrollPaginationCollection<Plan> plansScroll, UserScrapDictionary userScrapDictionary, UserOrderDictionary userOrderDictionary, User user) {
        if (plansScroll.isLastScroll()) {
            return newLastCursor(plansScroll.getCurrentScrollItems(), userScrapDictionary, userOrderDictionary, user);
        }
        return newCursorHasNext(plansScroll.getCurrentScrollItems(), userScrapDictionary, userOrderDictionary, user, plansScroll.getNextCursor().getId());
    }

    private static PlansScrollResponse newLastCursor(List<Plan> plans, UserScrapDictionary userScrapDictionary, UserOrderDictionary userOrderDictionary, @NotNull User user) {
        return newCursorHasNext(plans, userScrapDictionary, userOrderDictionary, user, LAST_CURSOR);
    }

    private static PlansScrollResponse newCursorHasNext(List<Plan> plans, UserScrapDictionary userScrapDictionary, UserOrderDictionary userOrderDictionary, User user, long nextCursor) {
        List<PlanInfoResponse> contents = plans.stream()
                .map(plan -> PlanInfoResponse.of(plan, user, getScarpStatus(plan, userScrapDictionary), getOrderStatus(plan, userOrderDictionary)))
                .collect(Collectors.toList());
        return new PlansScrollResponse(contents, nextCursor);
    }

    private static boolean getScarpStatus(Plan plan, UserScrapDictionary userScrapDictionary) {
        return userScrapDictionary.existByPlanId(plan.getId());
    }

    private static boolean getOrderStatus(Plan plan, UserOrderDictionary userOrderDictionary) {
        return userOrderDictionary.existByPlanId(plan.getId());
    }
}