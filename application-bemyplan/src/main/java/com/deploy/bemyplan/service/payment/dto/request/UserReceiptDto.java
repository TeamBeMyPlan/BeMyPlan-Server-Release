package com.deploy.bemyplan.service.payment.dto.request;

import com.deploy.bemyplan.external.client.apple.purchase.dto.request.AppleReceiptDto;
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
public class UserReceiptDto {

    @JsonProperty("receipt-data")
    private String receiptData;

    public static UserReceiptDto of(@JsonProperty("receipt-data") String receiptData) {
        return new UserReceiptDto(receiptData);
    }

    public AppleReceiptDto toClientDto(){
        return AppleReceiptDto.of(receiptData);
    }

}
