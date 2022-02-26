package com.deploy.bemyplan.domain.order.repository;

import com.deploy.bemyplan.domain.order.Order;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findAllByIds(List<Long> orderIds);
    List<Order> findByUserIdAndPlanIds(List<Long> planIds, Long userId);
}