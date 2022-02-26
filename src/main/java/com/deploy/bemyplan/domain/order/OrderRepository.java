package com.deploy.bemyplan.domain.order;

import com.deploy.bemyplan.domain.order.repository.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
