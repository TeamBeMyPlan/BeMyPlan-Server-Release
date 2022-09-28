package com.deploy.bemyplan.domain.order.repository;

import com.deploy.bemyplan.domain.order.Order;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findAllByIds(List<Long> orderIds);
    List<Order> findByUserIdAndPlanIds(List<Long> planIds, @Nullable Long userId);
}