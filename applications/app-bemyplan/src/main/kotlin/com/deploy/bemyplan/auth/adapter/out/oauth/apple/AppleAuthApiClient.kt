package com.deploy.bemyplan.auth.adapter.out.oauth.apple

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "appleAuthApiClient", url = "https://appleid.apple.com/auth")
internal interface AppleAuthApiClient {
    @GetMapping("/keys")
    fun getAppleAuthPublicKey(): ApplePublicKeyResponse
}