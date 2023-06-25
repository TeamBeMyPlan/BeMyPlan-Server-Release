package com.bemyplan.auth.adapter.out.oauth.kakao

import com.bemyplan.auth.adapter.out.oauth.AuthService
import org.springframework.stereotype.Component

@Component
internal class KaKaoAuthService(
    private val kakaoAuthApiCaller: KaKaoAuthApiClient,
) : AuthService {
    override fun getSocialId(token: String): String {
        val response: KaKaoProfileResponse = kakaoAuthApiCaller.getProfileInfo("Bearer " + token)
        return response.id
    }
}