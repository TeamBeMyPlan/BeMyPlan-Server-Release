package com.bemyplan.auth.adapter.out.oauth.kakao

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "kakaoAuthApiClient", url = "https://kapi.kakao.com")
internal interface KaKaoAuthApiClient {
    @GetMapping("/v2/user/me")
    fun getProfileInfo(@RequestHeader("Authorization") accessToken: String): KaKaoProfileResponse
}