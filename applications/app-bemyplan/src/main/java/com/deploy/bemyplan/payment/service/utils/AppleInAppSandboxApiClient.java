package com.deploy.bemyplan.payment.service.utils;

import com.deploy.bemyplan.payment.service.utils.dto.request.AppleReceiptDto;
import com.deploy.bemyplan.payment.service.utils.dto.response.AppStoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AppleInAppSandboxApiClient", url = "https://sandbox.itunes.apple.com")
public interface AppleInAppSandboxApiClient {

    @PostMapping("/verifyReceipt")
    AppStoreResponse validateReceiptSandbox(@RequestBody AppleReceiptDto userReceipt);
}
