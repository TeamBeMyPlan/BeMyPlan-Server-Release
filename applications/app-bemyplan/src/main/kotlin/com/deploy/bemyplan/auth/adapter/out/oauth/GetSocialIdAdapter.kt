package com.deploy.bemyplan.auth.adapter.out.oauth

import com.deploy.bemyplan.auth.application.port.out.GetSocialIdPort
import com.deploy.bemyplan.auth.application.port.out.GetSocialIdQuery
import org.springframework.stereotype.Component

@Component
internal class GetSocialIdAdapter(
    private val authServiceProvider: AuthServiceProvider,
) : GetSocialIdPort {
    override fun getSocialId(query: GetSocialIdQuery): String {
        val authService = authServiceProvider.getAuthService(query.socialType)
        return authService.getSocialId(query.token)
    }
}