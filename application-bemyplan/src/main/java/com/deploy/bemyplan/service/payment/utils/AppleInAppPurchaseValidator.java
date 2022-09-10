package com.deploy.bemyplan.service.payment.utils;

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

    //TODO 더 생길 시 ENUM 고려.
    private final static int ACCOUNT_ERROR = 21010;
    private final static int RECEIPT_ERROR = 21002;
    private final static int SANDBOX_TEST = 21007;
    private final AppleInAppProductionApiClient productionApiClient;
    private final AppleInAppSandboxApiClient sandboxApiClient;

    public Optional<AppStoreResponse> appleInAppPurchaseVerify(UserReceiptDto receiptRequest){
        AppStoreResponse appStoreResponse = productionApiClient.validateReceiptProduction(receiptRequest.toClientDto());
        switch (appStoreResponse.getStatus()){
            case RECEIPT_ERROR:
                throw new IllegalArgumentException("receipt-data가 잘못되었습니다. 정확한 receipt-date를 다시 보내주세요 " + appStoreResponse.getStatus());
            case ACCOUNT_ERROR:
                throw new IllegalArgumentException("만료되었거나 삭제된 계정입니다. " + appStoreResponse.getStatus());
            case SANDBOX_TEST:
                return Optional.of(sandboxApiClient.validateReceiptSandbox(receiptRequest.toClientDto()));
        }
        return Optional.of(appStoreResponse);
    }
}
