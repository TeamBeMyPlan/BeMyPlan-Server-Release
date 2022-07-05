package com.deploy.bemyplan.service.collection;

import com.deploy.bemyplan.domain.order.Order;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDictionary {

    /**
     * 특정 여행일정 게시글에 대한 구매기록 존재여부 판단
     */
    private final Map<Long, Order> dictionary;

    public static OrderDictionary of(List<Order> orders) {
        return new OrderDictionary(orders.stream()
                .collect(Collectors.toMap(Order::getPlanId, order -> order)));
    }

    public boolean existByPlanId(Long planId) {
        return dictionary.containsKey(planId);
    }
}
