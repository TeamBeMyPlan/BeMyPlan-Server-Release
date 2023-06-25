package com.bemyplan.auth.application.port.out

import com.deploy.bemyplan.domain.user.User
import com.deploy.bemyplan.domain.user.UserSocialType

interface GetUserPort {
    fun findById(userId: Long): User?

    fun findBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): User?

    fun existsByName(name: String): Boolean

    fun existsBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): Boolean
}