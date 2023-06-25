package com.bemyplan.auth.adapter.out.oauth.apple

import com.bemyplan.auth.adapter.out.oauth.AuthService
import org.springframework.stereotype.Component

@Component
internal class AppleAuthService(
    private val appleTokenDecoder: AppleTokenDecoder,
) : AuthService {

    override fun getSocialId(token: String): String {
        return appleTokenDecoder.getSocialIdFromIdToken(token)
    }
}