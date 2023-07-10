package com.deploy.bemyplan.payment.service.dto.request;

import com.deploy.bemyplan.payment.service.utils.dto.request.AppleReceiptDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserReceiptDto {

    @JsonProperty("receipt-data")
    private String receiptData;

    private UserReceiptDto(String receiptData) {
        this.receiptData = receiptData;
    }

    private UserReceiptDto() {
    }

    public static UserReceiptDto of(@JsonProperty("receipt-data") String receiptData) {
        return new UserReceiptDto(receiptData);
    }

    public AppleReceiptDto toClientDto() {
        return AppleReceiptDto.of(receiptData);
    }

    public String getReceiptData() {
        return this.receiptData;
    }

    public String toString() {
        return "UserReceiptDto(receiptData=" + this.getReceiptData() + ")";
    }
}
