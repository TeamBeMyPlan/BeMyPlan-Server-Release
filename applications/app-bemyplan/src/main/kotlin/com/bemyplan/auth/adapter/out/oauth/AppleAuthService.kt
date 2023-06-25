package com.bemyplan.auth.adapter.out.oauth

import com.deploy.bemyplan.auth.remote.apple.AppleTokenDecoder
import org.springframework.stereotype.Component

@Component
internal class AppleAuthService(
    private val appleTokenDecoder: AppleTokenDecoder,
) : AuthService {

    override fun getSocialId(token: String): String {
        return appleTokenDecoder!!.getSocialIdFromIdToken(token)
    }
}