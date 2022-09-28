package com.deploy.bemyplan.external.client.apple.purchase.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;

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
