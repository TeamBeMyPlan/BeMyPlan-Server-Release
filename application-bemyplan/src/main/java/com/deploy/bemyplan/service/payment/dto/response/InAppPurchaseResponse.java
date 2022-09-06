package com.deploy.bemyplan.service.payment.dto.response;

import com.deploy.bemyplan.domain.payment.PaymentState;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InAppPurchaseResponse {
    private final PaymentState status;
    private final String transactionId;

    public InAppPurchaseResponse(final PaymentState status, final String transactionId) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return PaymentState.COMPLETE == status;
    }

    public static InAppPurchaseResponse of(final PaymentState status, final String transactionId){
        InAppPurchaseResponse response = new InAppPurchaseResponse(
                status,
                transactionId
        );
        return response;
    }
}
