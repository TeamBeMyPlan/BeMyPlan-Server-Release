package com.deploy.bemyplan.service.order;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.service.order.dto.response.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.deploy.bemyplan.common.exception.ErrorCode.CONFLICT_ORDER_PLAN;
import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlanRepository planRepository;

    @Transactional
    public OrderResponseDto createOrder(final Long planId, final Long userId) {
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException("해당하는 일정이 없습니다.", NOT_FOUND_EXCEPTION));
        final Order order = orderRepository.findByPlanIdAndUserId(planId, userId)
                .orElseGet(() -> {
                    plan.updateOrderCnt();
                    return Order.of(planId, userId, OrderStatus.UNPAID, plan.getPrice());
                });

        orderRepository.save(order);

        return OrderResponseDto.of(order.getId());
    }

    @Transactional(readOnly = true)
    public void checkOrderStatus(final Long planId, final Long userId) {
        final Optional<Order> maybeOrder = orderRepository.findByPlanIdAndUserId(planId, userId);
        if (maybeOrder.isPresent() && OrderStatus.COMPLETED == maybeOrder.get().getStatus()) {
            throw new NotFoundException("이미 구매한 일정입니다.", CONFLICT_ORDER_PLAN);
        }
    }
}