package com.deploy.bemyplan.payment.service.utils.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AppleReceiptDto {

    @JsonProperty("receipt-data")
    private String receiptData;

    private AppleReceiptDto(String receiptData) {
        this.receiptData = receiptData;
    }

    private AppleReceiptDto() {
    }

    public static AppleReceiptDto of(@JsonProperty("receipt-data") String receiptData) {
        return new AppleReceiptDto(receiptData);
    }

    public String getReceiptData() {
        return this.receiptData;
    }

    public String toString() {
        return "AppleReceiptDto(receiptData=" + this.getReceiptData() + ")";
    }
}
