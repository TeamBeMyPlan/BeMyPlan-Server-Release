package com.deploy.bemyplan.auth.adapter.out.oauth.google

import com.deploy.bemyplan.auth.adapter.out.oauth.AuthService
import org.springframework.stereotype.Component

@Component
internal class GoogleAuthService(
    private val googleAuthApiCaller: GoogleAuthApiClient,
) : AuthService {

    override fun getSocialId(token: String): String {
        val response: GoogleProfileInfoResponse = googleAuthApiCaller.getProfileInfo("Bearer " + token)
        return response.id
    }
}