package com.deploy.bemyplan.payment.controller.dto.request;

import com.deploy.bemyplan.payment.service.dto.request.ConfirmOrderDto;

public final class ConfirmOrderRequest {
    private Long paymentId;
    private Long userId;

    protected ConfirmOrderRequest(Long paymentId, Long userId) {
        this.paymentId = paymentId;
        this.userId = userId;
    }

    protected ConfirmOrderRequest() {
    }

    public ConfirmOrderDto toServiceDto(Long paymentId, Long userId) {
        return ConfirmOrderDto.of(paymentId, userId);
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String toString() {
        return "ConfirmOrderRequest(paymentId=" + this.getPaymentId() + ", userId=" + this.getUserId() + ")";
    }
}