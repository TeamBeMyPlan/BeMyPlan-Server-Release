package com.bemyplan.auth.adapter.out.oauth

import com.deploy.bemyplan.auth.remote.google.GoogleAuthApiClient
import com.deploy.bemyplan.auth.remote.google.GoogleProfileInfoResponse
import org.springframework.stereotype.Component

@Component
class GoogleAuthService(
    private val googleAuthApiCaller: GoogleAuthApiClient,
) : AuthService {

    override fun getSocialId(token: String): String {
        val response: GoogleProfileInfoResponse = googleAuthApiCaller.getProfileInfo("Bearer " + token)
        return response.getId()
    }
}