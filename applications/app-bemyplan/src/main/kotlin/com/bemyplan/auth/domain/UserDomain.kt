package com.bemyplan.auth.domain

class UserDomain(
    val id: Long,
    val nickname: String,
    val email: String,
    val active: Boolean,
    val socialInfo: SocialDomain,
)