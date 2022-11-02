package com.deploy.bemyplan.payment.service.utils.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppleReceiptDto {

    @JsonProperty("receipt-data")
    private String receiptData;

    public static AppleReceiptDto of(@JsonProperty("receipt-data") String receiptData){
        return new AppleReceiptDto(receiptData);
    }
}
