package com.deploy.bemyplan.external.client.kakao;

import com.deploy.bemyplan.external.client.kakao.dto.response.KaKaoProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name ="kakaoAuthApiClient", url = "https://kapi.kakao.com")
public interface KaKaoAuthApiClient {

    @GetMapping("/v2/user/me")
    KaKaoProfileResponse getProfileInfo(@RequestHeader("Authorization") String accessToken);
}