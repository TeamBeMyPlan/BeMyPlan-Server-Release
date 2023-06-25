package com.bemyplan.auth.adapter.out.infra

import com.bemyplan.auth.application.port.out.GetUserPort
import com.deploy.bemyplan.domain.user.User
import com.deploy.bemyplan.domain.user.UserRepository
import com.deploy.bemyplan.domain.user.UserSocialType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
internal class GetUserAdapter(
    private val userRepository: UserRepository,
): GetUserPort {
    override fun findById(userId: Long): User? {
        return userRepository.findByIdOrNull(userId)
    }

    override fun findBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): User? {
        return userRepository.findUserBySocialIdAndSocialType(socialId, socialType)
    }

    override fun existsByName(name: String): Boolean {
        return userRepository.existsByName(name)
    }

    override fun existsBySocialIdAndSocialType(socialId: String, socialType: UserSocialType): Boolean {
        return userRepository.existsBySocialIdAndSocialType(socialId, socialType)
    }
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrNull(id: ID): T? = findById(id).orElse(null)