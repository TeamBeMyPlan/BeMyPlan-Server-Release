package com.deploy.bemyplan.domain.order;

import com.deploy.bemyplan.domain.order.repository.OrderRepositoryCustom;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    List<Order> findAllByUserId(Long userId);
    Optional<Order> findByPlanIdAndUserId(Long planId, @Nullable Long userId);

    Boolean existsOrderByUserIdAndPlanIdAndStatus(Long userId, Long planId, OrderStatus orderStatus);
}
