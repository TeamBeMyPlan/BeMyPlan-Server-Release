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

import static com.deploy.bemyplan.common.exception.ErrorCode.CONFLICT_ORDER_PLAN;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlanRepository planRepository;

    @Transactional
    public void createOrder(Long planId, Long userId) {
        Order maybeOrder = orderRepository.findByUserIdAndPlanId(planId, userId);
        if (maybeOrder != null){
            throw new NotFoundException("이미 구매한 일정입니다.", CONFLICT_ORDER_PLAN);
        }
        Plan plan = planRepository.findPlanById(planId);
        plan.updateOrderCnt();
        orderRepository.save(Order.of(planId, userId, OrderStatus.PASSIVE, plan.getPrice()));
    }

    @Transactional(readOnly = true)
    public void checkOrderStatus(Long planId, Long userId) {
        Order order = orderRepository.findByUserIdAndPlanId(planId, userId);
        if (order != null){
            throw new NotFoundException("이미 구매한 일정입니다.", CONFLICT_ORDER_PLAN);
        }
    }
}