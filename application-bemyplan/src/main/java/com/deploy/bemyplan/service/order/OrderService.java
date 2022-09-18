package com.deploy.bemyplan.service.order;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.deploy.bemyplan.common.exception.ErrorCode.CONFLICT_ORDER_PLAN;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlanRepository planRepository;

    @Transactional
    public void createOrder(final Long planId, final Long userId) {
        final Plan plan = planRepository.findPlanById(planId);
        plan.updateOrderCnt();
        orderRepository.save(Order.of(planId, userId, OrderStatus.UNPAID, plan.getPrice()));
    }

    @Transactional(readOnly = true)
    public void checkOrderStatus(final Long planId, final Long userId) {
        final Optional<Order> maybeOrder = orderRepository.findByPlanIdAndUserId(planId, userId);
        if (maybeOrder.isPresent() && OrderStatus.COMPLETED == maybeOrder.get().getStatus()) {
            throw new NotFoundException("이미 구매한 일정입니다.", CONFLICT_ORDER_PLAN);
        }
    }
}