package com.deploy.bemyplan.payment.service.utils;

import com.deploy.bemyplan.payment.service.dto.request.UserReceiptDto;
import com.deploy.bemyplan.payment.service.utils.dto.response.AppStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppleInAppPurchaseValidator {

    //TODO 더 생길 시 ENUM 고려.
    private static final int ACCOUNT_ERROR = 21010;
    private static final int RECEIPT_ERROR = 21002;
    private static final int SANDBOX_TEST = 21007;
    private final AppleInAppProductionApiClient productionApiClient;
    private final AppleInAppSandboxApiClient sandboxApiClient;

    public Optional<AppStoreResponse> appleInAppPurchaseVerify(final UserReceiptDto receiptRequest){
        final AppStoreResponse appStoreResponse = productionApiClient.validateReceiptProduction(receiptRequest.toClientDto());
        switch (appStoreResponse.getStatus()){
            case RECEIPT_ERROR:
                throw new IllegalArgumentException("receipt-data가 잘못되었습니다. 정확한 receipt-date를 다시 보내주세요 " + RECEIPT_ERROR);
            case ACCOUNT_ERROR:
                throw new IllegalArgumentException("만료되었거나 삭제된 계정입니다. " + ACCOUNT_ERROR);
            case SANDBOX_TEST:
                return Optional.of(sandboxApiClient.validateReceiptSandbox(receiptRequest.toClientDto()));
        }
        return Optional.of(appStoreResponse);
    }
}
