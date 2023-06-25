package com.bemyplan.auth.application.port.out

import com.deploy.bemyplan.domain.user.UserSocialType

data class GetSocialIdQuery(
    val token: String,
    val socialType: UserSocialType,
)
