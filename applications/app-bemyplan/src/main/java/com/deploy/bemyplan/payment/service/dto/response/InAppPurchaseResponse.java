package com.deploy.bemyplan.payment.service.dto.response;

import com.deploy.bemyplan.domain.payment.PaymentState;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InAppPurchaseResponse {
    private final Long id;
    private final PaymentState status;
    private final String transactionId;

    private InAppPurchaseResponse(final Long id, final PaymentState status, final String transactionId) {
        this.id = id;
        this.status = status;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return PaymentState.COMPLETE == status;
    }

    public static InAppPurchaseResponse of(final Long id, final PaymentState status, final String transactionId) {
        InAppPurchaseResponse response = new InAppPurchaseResponse(
                id,
                status,
                transactionId
        );
        return response;
    }
}
