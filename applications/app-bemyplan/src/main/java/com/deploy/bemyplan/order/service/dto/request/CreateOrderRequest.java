package com.deploy.bemyplan.order.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrderRequest {

    @NotNull(message = "{plan.id.notNull}")
    private Long planId;

    @NotNull
    private int orderPrice;

    public Long getPlanId() {
        return planId;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
}