package com.deploy.bemyplan.payment.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
public class ConfirmOrderDto {
    private Long paymentId;
    private Long userId;

    public static ConfirmOrderDto of(Long paymentId, Long userId) {
        return new ConfirmOrderDto(paymentId, userId);
    }
}
