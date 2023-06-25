package com.bemyplan.auth.adapter.out.infra

import com.bemyplan.auth.application.UserDeleteEvent
import com.bemyplan.auth.application.port.out.SaveUserPort
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
    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun delete(user: User, reasonForWithdrawal: String) {
        userRepository.delete(user)
        withdrawalUserRepository.save(WithdrawalUser.newInstance(user, reasonForWithdrawal))
        eventPublisher.publishEvent(UserDeleteEvent(user.id))
    }
}