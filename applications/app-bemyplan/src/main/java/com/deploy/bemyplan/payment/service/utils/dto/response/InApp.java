package com.deploy.bemyplan.payment.service.utils.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class InApp {
    @JsonAlias("is_trial_period")
    private String isTrialPeriod;
    @JsonAlias("original_purchase_date")
    private String originalPurchaseDate;
    @JsonAlias("original_purchase_date_ms")
    private String originalPurchaseDateMs;
    @JsonAlias("original_purchase_date_pst")
    private String originalPurchaseDatePst;
    @JsonAlias("original_transaction_id")
    private String originalTransactionId;
    @JsonAlias("product_id")
    private String productId;
    @JsonAlias("purchase_date")
    private String purchaseDate;
    @JsonAlias("purchase_date_ms")
    private String purchaseDateMs;
    @JsonAlias("purchase_date_pst")
    private String purchaseDatePst;
    private String quantity;
    @JsonAlias("transaction_id")
    private String transactionId;

    public String getIsTrialPeriod() {
        return this.isTrialPeriod;
    }

    public String getOriginalPurchaseDate() {
        return this.originalPurchaseDate;
    }

    public String getOriginalPurchaseDateMs() {
        return this.originalPurchaseDateMs;
    }

    public String getOriginalPurchaseDatePst() {
        return this.originalPurchaseDatePst;
    }

    public String getOriginalTransactionId() {
        return this.originalTransactionId;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getPurchaseDate() {
        return this.purchaseDate;
    }

    public String getPurchaseDateMs() {
        return this.purchaseDateMs;
    }

    public String getPurchaseDatePst() {
        return this.purchaseDatePst;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public String toString() {
        return "InApp(isTrialPeriod=" + this.getIsTrialPeriod() + ", originalPurchaseDate=" + this.getOriginalPurchaseDate() + ", originalPurchaseDateMs=" + this.getOriginalPurchaseDateMs() + ", originalPurchaseDatePst=" + this.getOriginalPurchaseDatePst() + ", originalTransactionId=" + this.getOriginalTransactionId() + ", productId=" + this.getProductId() + ", purchaseDate=" + this.getPurchaseDate() + ", purchaseDateMs=" + this.getPurchaseDateMs() + ", purchaseDatePst=" + this.getPurchaseDatePst() + ", quantity=" + this.getQuantity() + ", transactionId=" + this.getTransactionId() + ")";
    }
}
