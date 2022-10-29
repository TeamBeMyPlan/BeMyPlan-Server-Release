package com.deploy.bemyplan.auth.remote.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "appleAuthApiClient", url = "https://appleid.apple.com/auth")
public interface AppleAuthApiClient {

    @GetMapping(value = "/keys")
    ApplePublicKeyResponse getAppleAuthPublicKey();
}