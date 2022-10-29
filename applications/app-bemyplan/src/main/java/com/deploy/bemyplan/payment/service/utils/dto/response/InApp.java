package com.deploy.bemyplan.payment.service.utils.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
}
