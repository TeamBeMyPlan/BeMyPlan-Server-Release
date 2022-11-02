package com.deploy.bemyplan.payment.controller.dto.request;


import com.deploy.bemyplan.payment.service.dto.request.UserReceiptDto;
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
public class UserPurchaseReceipt {
    @JsonProperty("receipt-data")
    private String receiptData;

    public UserReceiptDto toServiceDto(){
        return UserReceiptDto.of(receiptData);
    }
}
