package com.deploy.bemyplan.service.order.dto.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponseDto {

    private Long orderId;

    private OrderResponseDto(final Long orderId) {
        this.orderId = orderId;
    }

    public static OrderResponseDto of(final Long orderId){
        OrderResponseDto response = new OrderResponseDto(
                orderId
        );
        return response;
    }
}
