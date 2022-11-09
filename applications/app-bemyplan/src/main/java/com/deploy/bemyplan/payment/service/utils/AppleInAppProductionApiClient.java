package com.deploy.bemyplan.payment.service.utils;

import com.deploy.bemyplan.payment.service.utils.dto.request.AppleReceiptDto;
import com.deploy.bemyplan.payment.service.utils.dto.response.AppStoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AppleInAppProductionApiClient", url = "https://buy.itunes.apple.com")
public interface AppleInAppProductionApiClient {

    @PostMapping("/verifyReceipt")
    AppStoreResponse validateReceiptProduction(@RequestBody AppleReceiptDto userReceipt);
}
