package com.deploy.bemyplan.payment.controller.dto.request;

import com.deploy.bemyplan.payment.service.dto.request.ConfirmOrderDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public final class ConfirmOrderRequest {
    private Long paymentId;
    private Long userId;

    public ConfirmOrderDto toServiceDto(Long paymentId, Long userId) {
        return ConfirmOrderDto.of(paymentId, userId);
    }
}