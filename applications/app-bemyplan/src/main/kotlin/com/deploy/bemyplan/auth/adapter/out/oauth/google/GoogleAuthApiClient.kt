package com.deploy.bemyplan.auth.adapter.out.oauth.google

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "googleAuthApiClient", url = "https://www.googleapis.com")
internal interface GoogleAuthApiClient {
    @GetMapping("/oauth2/v2/userinfo")
    fun getProfileInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String): GoogleProfileInfoResponse
}