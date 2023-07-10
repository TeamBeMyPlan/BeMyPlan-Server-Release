package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.domain.order.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDictionary {

    /**
     * 특정 여행일정 게시글에 대한 구매기록 존재여부 판단
     */
    private final Map<Long, Order> dictionary;

    private OrderDictionary(Map<Long, Order> dictionary) {
        this.dictionary = dictionary;
    }

    public static OrderDictionary of(List<Order> orders) {
        return new OrderDictionary(orders.stream()
                .collect(Collectors.toMap(Order::getPlanId, order -> order)));
    }

    public boolean existByPlanId(Long planId) {
        return dictionary.containsKey(planId);
    }
}
