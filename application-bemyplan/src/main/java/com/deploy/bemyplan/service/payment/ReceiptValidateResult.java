package com.deploy.bemyplan.service.payment;

class ReceiptValidateResult {
    private final int status;
    private final String transactionId;

    public ReceiptValidateResult(final int status, final String transactionId) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return 0 == status;
    }
}
