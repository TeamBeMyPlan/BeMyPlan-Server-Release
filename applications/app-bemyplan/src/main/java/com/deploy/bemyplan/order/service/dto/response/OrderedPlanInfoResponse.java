package com.deploy.bemyplan.order.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.OrderedPlan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderedPlanInfoResponse extends AuditingTimeResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private int orderPrice;

    private OrderedPlanInfoResponse(final Long planId, final String thumbnailUrl, final String title, final int orderPrice) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.orderPrice = orderPrice;
    }

    public static OrderedPlanInfoResponse of(OrderedPlan orderedPlan) {
        OrderedPlanInfoResponse response = new OrderedPlanInfoResponse(
                orderedPlan.getId(),
                orderedPlan.getThumbnailUrl(),
                orderedPlan.getTitle(),
                orderedPlan.getOrderPrice()
        );

        response.setBaseTime(orderedPlan.getCreatedAt(), orderedPlan.getUpdatedAt());
        return response;
    }
}
