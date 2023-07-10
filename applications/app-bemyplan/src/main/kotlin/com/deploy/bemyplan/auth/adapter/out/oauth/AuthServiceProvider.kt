package com.deploy.bemyplan.auth.adapter.out.oauth

import com.deploy.bemyplan.auth.adapter.out.oauth.apple.AppleAuthService
import com.deploy.bemyplan.auth.adapter.out.oauth.google.GoogleAuthService
import com.deploy.bemyplan.auth.adapter.out.oauth.kakao.KaKaoAuthService
import com.deploy.bemyplan.domain.user.UserSocialType
import org.springframework.stereotype.Component

@Component
internal class AuthServiceProvider(
    appleAuthService: AppleAuthService,
    kaKaoAuthService: KaKaoAuthService,
    googleAuthService: GoogleAuthService,
) {

    init {
        authServiceMap[UserSocialType.APPLE] = appleAuthService
        authServiceMap[UserSocialType.KAKAO] = kaKaoAuthService
        authServiceMap[UserSocialType.GOOGLE] = googleAuthService
    }

    fun getAuthService(socialType: UserSocialType): AuthService {
        return authServiceMap[socialType]!!
    }

    companion object {
        private val authServiceMap: MutableMap<UserSocialType, AuthService> = HashMap()
    }
}