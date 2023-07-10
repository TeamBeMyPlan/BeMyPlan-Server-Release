package com.deploy.bemyplan.order.controller;

import com.deploy.bemyplan.common.controller.ResponseDTO;
import com.deploy.bemyplan.config.auth.Auth;
import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.order.service.OrderService;
import com.deploy.bemyplan.order.service.dto.OrderListResponse;
import com.deploy.bemyplan.order.service.dto.request.CreateOrderRequest;
import com.deploy.bemyplan.order.service.dto.response.OrderResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation("[인증] 여행일정 페이지 - 해당 여행일정을 구매합니다.")
    @Auth
    @PostMapping("/v1/orders")
    public OrderResponseDto createOrder(@Valid @RequestBody final CreateOrderRequest request, @UserId final Long userId) {
        return orderService.createOrder(request.getPlanId(), request.getOrderPrice(), userId);
    }

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정 구매 여부를 확인합니다. (성공 시 구매하지 않은 상태)")
    @Auth
    @GetMapping("/v1/orders/{planId}")
    public ResponseDTO checkOrderStatus(@PathVariable final Long planId, @UserId final Long userId) {
        orderService.checkOrderStatus(planId, userId);
        return ResponseDTO.of("해당 여행일정을 구매할 수 있습니다.");
    }

    @ApiOperation("[인증] 내가 구매한 여행 일정 내역을 조회합니다.")
    @Auth
    @GetMapping("/v1/orders")
    public OrderListResponse getMyOrderedPlanList(@UserId final Long userId) {
        return orderService.getOrderedPlanList(userId);
    }
}
