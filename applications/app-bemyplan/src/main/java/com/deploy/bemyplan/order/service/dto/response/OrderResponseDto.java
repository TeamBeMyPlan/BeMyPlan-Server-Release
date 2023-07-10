package com.deploy.bemyplan.order.service.dto.response;


public class OrderResponseDto {

    private Long orderId;

    private OrderResponseDto(final Long orderId) {
        this.orderId = orderId;
    }

    private OrderResponseDto() {
    }

    public static OrderResponseDto of(final Long orderId) {
        OrderResponseDto response = new OrderResponseDto(
                orderId
        );
        return response;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public String toString() {
        return "OrderResponseDto(orderId=" + this.getOrderId() + ")";
    }
}
