package com.deploy.bemyplan.auth.application.port.out

import com.deploy.bemyplan.auth.domain.UserDomain
import com.deploy.bemyplan.domain.user.UserSocialType

interface GetUserPort {
    fun findById(userId: Long): UserDomain?

    fun findBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): UserDomain?

    fun existsByName(name: String): Boolean

    fun existsBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): Boolean
}