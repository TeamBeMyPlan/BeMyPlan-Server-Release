package com.deploy.bemyplan.payment.service.dto.request;

import com.deploy.bemyplan.payment.service.utils.dto.request.AppleReceiptDto;
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
