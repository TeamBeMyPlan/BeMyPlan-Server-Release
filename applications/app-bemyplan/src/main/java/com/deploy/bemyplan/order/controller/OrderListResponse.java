package com.deploy.bemyplan.order.controller;

import com.deploy.bemyplan.order.service.dto.response.OrderedPlanInfoResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderListResponse {
    List<OrderedPlanInfoResponse> contents = new ArrayList<>();

    private OrderListResponse(final List<OrderedPlanInfoResponse> contents) {
        this.contents.addAll(contents);
    }

    public static OrderListResponse of(final List<OrderedPlanInfoResponse> contents) {
        return new OrderListResponse(contents);
    }
}
