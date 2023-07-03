package com.deploy.bemyplan.auth.domain

import com.deploy.bemyplan.domain.user.UserSocialType

data class SocialDomain(
    val socialId: String,
    val socialType: UserSocialType,
)