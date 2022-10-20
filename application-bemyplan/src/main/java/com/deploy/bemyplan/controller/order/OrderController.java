package com.deploy.bemyplan.controller.order;

import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.controller.common.response.ResponseDTO;
import com.deploy.bemyplan.service.order.OrderService;
import com.deploy.bemyplan.service.order.dto.request.CreateOrderRequest;
import com.deploy.bemyplan.service.order.dto.response.OrderListResponse;
import com.deploy.bemyplan.service.order.dto.response.OrderResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public OrderResponseDto createOrder(@Valid @RequestBody final CreateOrderRequest request, @UserId final Long userId) {
        return orderService.createOrder(request.getPlanId(), request.getOrderPrice(), userId);
    }

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정 구매 여부를 확인합니다. (성공 시 구매하지 않은 상태)")
    @Auth
    @GetMapping("/v1/plan/order/{planId}")
    public ResponseDTO checkOrderStatus(@PathVariable final Long planId, @UserId final Long userId) {
        orderService.checkOrderStatus(planId, userId);
        return ResponseDTO.of("해당 여행일정을 구매할 수 있습니다.");
    }

    @ApiOperation("[인증] 내가 구매한 여행 일정 내역을 조회합니다.")
    @Auth
    @GetMapping("/v1/order/plans")
    public OrderListResponse getMyOrderedPlanList(@UserId final Long userId) {
        return null;
        //        return orderService.getMyOrderList(userId);
    }
}
