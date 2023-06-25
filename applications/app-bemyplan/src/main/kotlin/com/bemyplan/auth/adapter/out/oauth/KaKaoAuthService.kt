package com.bemyplan.auth.adapter.out.oauth

import com.deploy.bemyplan.auth.remote.kakao.KaKaoAuthApiClient
import com.deploy.bemyplan.auth.remote.kakao.KaKaoProfileResponse
import org.springframework.stereotype.Component

@Component
internal class KaKaoAuthService(
    private val kakaoAuthApiCaller: KaKaoAuthApiClient,
) : AuthService {
    override fun getSocialId(token: String): String {
        val response: KaKaoProfileResponse = kakaoAuthApiCaller.getProfileInfo("Bearer " + token)
        return response.getId()
    }
}