package com.deploy.bemyplan.service.payment.utils;

import com.deploy.bemyplan.common.exception.model.ValidationException;
import com.deploy.bemyplan.external.client.apple.purchase.AppleInAppProductionApiClient;
import com.deploy.bemyplan.external.client.apple.purchase.AppleInAppSandboxApiClient;
import com.deploy.bemyplan.service.payment.dto.request.UserReceiptDto;
import com.deploy.bemyplan.external.client.apple.purchase.dto.response.AppStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppleInAppPurchaseValidator {

    private final AppleInAppProductionApiClient productionApiClient;
    private final AppleInAppSandboxApiClient sandboxApiClient;

    public Optional<AppStoreResponse> appleInAppPurchaseVerify(UserReceiptDto receiptRequest){
        AppStoreResponse response = productionApiClient.validateReceiptProduction(receiptRequest.toClientDto());

        //TODO switch case 고려
        if (response.getStatus() == 21002){
            throw new IllegalArgumentException("receipt-data가 잘못되었습니다. 정확한 receipt-date를 다시 보내주세요 " + response.getStatus());
        }
        if (response.getStatus() == 21000){
            throw new IllegalArgumentException("잘못된 Http Method 요청입니다. " + response.getStatus());
        }
        if(response.getStatus() == 21010){
            throw new IllegalArgumentException("만료되었거나 삭제된 계정입니다. " + response.getStatus());
        }

        AppStoreResponse appStoreResponse = sandboxApiClient.validateReceiptSandbox(receiptRequest.toClientDto());

        return Optional.ofNullable(appStoreResponse);
    }
}
