package com.deploy.bemyplan.auth.domain

class UserDomain(
    val id: Long? = null,
    val nickname: String,
    val email: String,
    val socialInfo: SocialDomain,
    var active: Boolean = true,
) {
    fun inactive() {
        this.active = false
    }
}