package com.deploy.bemyplan.controller.order;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.service.order.OrderService;
import com.deploy.bemyplan.service.order.dto.request.CreateOrderRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation("[인증] 여행일정 페이지 - 해당 여행일정을 구매합니다.")
    @Auth
    @PostMapping("/v1/plan/order")
    public ApiResponse<String> createOrder(@Valid @RequestBody CreateOrderRequest request, @UserId Long userId) {
        orderService.createOrder(request.getPlanId(), userId);
        return ApiResponse.SUCCESS;
    }
}
