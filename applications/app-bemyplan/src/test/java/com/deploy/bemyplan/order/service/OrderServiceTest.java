package com.deploy.bemyplan.order.service;

import com.deploy.bemyplan.domain.order.Order;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.order.service.dto.response.OrderResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository spyOrderRepository;
    private PlanRepository spyPlanRepository;

    @BeforeEach
    void setUp() {
        spyOrderRepository = mock(OrderRepository.class);
        spyPlanRepository = mock(PlanRepository.class);
        orderService = new OrderService(spyOrderRepository, spyPlanRepository);
    }

    @Test
    void createOrderReturnsExistsOrderWhenExistsOrder() {
        Plan givenPlan = Plan.newInstance(2L, RegionCategory.JEJU, Region.JEJUALL, "thumbnailUrl", "title", "description", TagInfo.testBuilder().build(), 10000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        Order existedOrder = Order.of(givenPlan.getId(), 1L, OrderStatus.UNPAID, givenPlan.getPrice());
        given(spyPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));
        given(spyOrderRepository.findByPlanIdAndUserId(givenPlan.getId(), 1L)).willReturn(Optional.of(existedOrder));

        OrderResponseDto result = orderService.createOrder(givenPlan.getId(), 1000, 1L);

        Assertions.assertThat(result.getOrderId()).isEqualTo(existedOrder.getId());
    }

    @Test
    void createOrderNoUpdateOrderCntWhenExistsOrder() {
        Plan givenPlan = Plan.newInstance(2L, RegionCategory.JEJU, Region.JEJUALL, "thumbnailUrl", "title", "description", TagInfo.testBuilder().build(), 10000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        Order existedOrder = Order.of(givenPlan.getId(), 1L, OrderStatus.UNPAID, givenPlan.getPrice());
        int previousOrderCount = givenPlan.getOrderCnt();
        given(spyPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));
        given(spyOrderRepository.findByPlanIdAndUserId(givenPlan.getId(), 1L)).willReturn(Optional.of(existedOrder));

        orderService.createOrder(givenPlan.getId(), 1000, 1L);

        Assertions.assertThat(givenPlan.getOrderCnt()).isEqualTo(previousOrderCount);
    }

    @Test
    void createOrderUpdatesOrderCntWhenNoExistOrder() {
        Plan givenPlan = Plan.newInstance(2L, RegionCategory.JEJU, Region.JEJUALL, "thumbnailUrl", "title", "description", TagInfo.testBuilder().build(), 10000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        int previousOrderCount = givenPlan.getOrderCnt();
        given(spyPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));

        orderService.createOrder(givenPlan.getId(), 1000, 1L);

        Assertions.assertThat(givenPlan.getOrderCnt()).isEqualTo(previousOrderCount + 1);
    }

    @Test
    void getOrderedPlanListCallsPlanRepository() {
        orderService.getOrderedPlanList(1L);

        verify(spyPlanRepository, times(1)).findAllByOrderAndUserId(1L);
    }
}