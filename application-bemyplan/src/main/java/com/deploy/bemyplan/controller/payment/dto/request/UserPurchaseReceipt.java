package com.deploy.bemyplan.controller.payment.dto.request;


import com.deploy.bemyplan.service.payment.dto.request.UserReceiptDto;
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
public class UserPurchaseReceipt {
    @JsonProperty("receipt-data")
    private String receiptData;

    public UserReceiptDto toServiceDto(){
        return UserReceiptDto.of(receiptData);
    }
}
