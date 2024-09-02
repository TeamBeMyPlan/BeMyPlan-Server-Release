package com.deploy.bemyplan.auth.adapter.out.infra

import com.deploy.bemyplan.auth.application.port.out.GetUserPort
import com.deploy.bemyplan.auth.domain.SocialDomain
import com.deploy.bemyplan.auth.domain.UserDomain
import com.deploy.bemyplan.domain.user.UserRepository
import com.deploy.bemyplan.domain.user.UserSocialType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
internal class GetUserAdapter(
    private val userRepository: UserRepository,
): GetUserPort {
    override fun findById(userId: Long): UserDomain? {
        return userRepository.findByIdOrNull(userId)?.let {
            UserDomain(
                it.id,
                it.nickname,
                it.email,
                SocialDomain(it.socialId, it.socialType),
                it.isActive,
            )
        }
    }

    override fun findBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): UserDomain? {
        return userRepository.findUserBySocialIdAndSocialType(socialId, socialType)?.let {
            UserDomain(
                it.id,
                it.nickname,
                it.email,
                SocialDomain(it.socialId, it.socialType),
                it.isActive,
            )
        }
    }

    override fun existsByName(name: String): Boolean {
        return userRepository.existsByName(name)
    }

    override fun existsBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): Boolean {
        return userRepository.existsBySocialIdAndSocialType(socialId, socialType)
    }
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrNull(id: ID): T? = findById(id).orElse(null)