package com.bemyplan.auth.domain

class UserDomain(
    val id: Long? = null,
    val nickname: String,
    val email: String,
    var active: Boolean,
    val socialInfo: SocialDomain,
) {
    fun inactive() {
        this.active = false
    }
}