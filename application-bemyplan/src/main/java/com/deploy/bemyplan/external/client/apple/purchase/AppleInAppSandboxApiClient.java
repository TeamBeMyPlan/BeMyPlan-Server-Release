package com.deploy.bemyplan.external.client.apple.purchase;

import com.deploy.bemyplan.external.client.apple.purchase.dto.request.AppleReceiptDto;
import com.deploy.bemyplan.external.client.apple.purchase.dto.response.AppStoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AppleInAppSandboxApiClient", url = "https://sandbox.itunes.apple.com")
public interface AppleInAppSandboxApiClient {

    @PostMapping("/verifyReceipt")
    AppStoreResponse validateReceiptSandbox(@RequestBody AppleReceiptDto userReceipt);
}
