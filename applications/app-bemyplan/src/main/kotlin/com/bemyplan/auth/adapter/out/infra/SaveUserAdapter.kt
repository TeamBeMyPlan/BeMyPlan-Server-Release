package com.bemyplan.auth.adapter.out.infra

import com.bemyplan.auth.application.UserDeleteEvent
import com.bemyplan.auth.application.port.out.SaveUserPort
import com.bemyplan.auth.domain.UserDomain
import com.deploy.bemyplan.domain.user.SocialInfo
import com.deploy.bemyplan.domain.user.User
import com.deploy.bemyplan.domain.user.UserRepository
import com.deploy.bemyplan.domain.user.WithdrawalUser
import com.deploy.bemyplan.domain.user.WithdrawalUserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
internal class SaveUserAdapter(
    private val userRepository: UserRepository,
    private val withdrawalUserRepository: WithdrawalUserRepository,
    private val eventPublisher: ApplicationEventPublisher,
): SaveUserPort{
    override fun save(user: UserDomain) {
        userRepository.save(
            User.newInstance(user.socialInfo.socialId,
                user.socialInfo.socialType,
                user.nickname,
                user.email)
        )
    }

    override fun delete(user: UserDomain, reasonForWithdrawal: String) {
        val userEntity = User(
            user.id,
            user.nickname,
            user.email,
            user.active,
            SocialInfo.of(user.socialInfo.socialId, user.socialInfo.socialType)
        )

        userRepository.delete(userEntity)
        withdrawalUserRepository.save(WithdrawalUser.newInstance(userEntity, reasonForWithdrawal))
        eventPublisher.publishEvent(UserDeleteEvent(userEntity.id))
    }
}