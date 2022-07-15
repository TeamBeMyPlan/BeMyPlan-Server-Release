package com.deploy.bemyplan.service.payment;

import org.springframework.stereotype.Component;

@Component
public class MockPaymentClient implements PaymentClient {
    @Override
    public ReceiptValidateResult validate(PurchaseReceipt receipt) {
        return new ReceiptValidateResult(0, "1234");
    }
}
