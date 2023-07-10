package com.deploy.bemyplan.payment.service.dto.request;

public class ConfirmOrderDto {
    private Long paymentId;
    private Long userId;

    private ConfirmOrderDto(Long paymentId, Long userId) {
        this.paymentId = paymentId;
        this.userId = userId;
    }

    private ConfirmOrderDto() {
    }

    public static ConfirmOrderDto of(Long paymentId, Long userId) {
        return new ConfirmOrderDto(paymentId, userId);
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ConfirmOrderDto)) return false;
        final ConfirmOrderDto other = (ConfirmOrderDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$paymentId = this.getPaymentId();
        final Object other$paymentId = other.getPaymentId();
        if (this$paymentId == null ? other$paymentId != null : !this$paymentId.equals(other$paymentId)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConfirmOrderDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $paymentId = this.getPaymentId();
        result = result * PRIME + ($paymentId == null ? 43 : $paymentId.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        return result;
    }
}
