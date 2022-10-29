package com.deploy.bemyplan.auth.remote.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "googleAuthApiClient", url = "https://www.googleapis.com")
public interface GoogleAuthApiClient {

    @GetMapping("/oauth2/v2/userinfo")
    GoogleProfileInfoResponse getProfileInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}