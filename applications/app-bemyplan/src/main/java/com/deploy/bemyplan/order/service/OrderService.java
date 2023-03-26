package com.deploy.bemyplan.order.service;

import com.deploy.bemyplan.common.exception.model.ConflictException;
import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.plan.OrderedPlan;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.order.service.dto.response.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlanRepository planRepository;

    @Transactional
    public OrderResponseDto createOrder(final Long planId, final int orderPrice, final Long userId) {
        final Plan plan = getPlanWithValidation(planId, userId);
        final Order order = orderRepository.findByPlanIdAndUserId(planId, userId)
                .orElseGet(() -> {
                    plan.updateOrderCnt();
                    return Order.of(planId, userId, OrderStatus.UNPAID, orderPrice);
                });

        orderRepository.save(order);

        return OrderResponseDto.of(order.getId());
    }

    @Transactional(readOnly = true)
    public void checkOrderStatus(final Long planId, final Long userId) {
        getPlanWithValidation(planId, userId);

        final Optional<Order> maybeOrder = orderRepository.findByPlanIdAndUserId(planId, userId);
        if (maybeOrder.isPresent() && OrderStatus.COMPLETED == maybeOrder.get().getStatus()) {
            throw new ConflictException("이미 구매한 일정입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<OrderedPlan> getOrderedPlanList(final Long userId) {
        return planRepository.findAllByOrderAndUserId(userId);
    }

    @NotNull
    private Plan getPlanWithValidation(Long planId, Long userId) {
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException("해당하는 일정이 없습니다."));
        if (Objects.equals(plan.getCreatorId(), userId)) throw new ConflictException("해당 일정의 크리에이터입니다.");
        return plan;
    }
}