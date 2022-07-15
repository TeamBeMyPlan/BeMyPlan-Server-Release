package com.deploy.bemyplan.service.payment;

interface PaymentClient {
    ReceiptValidateResult validate(PurchaseReceipt receipt);
}
