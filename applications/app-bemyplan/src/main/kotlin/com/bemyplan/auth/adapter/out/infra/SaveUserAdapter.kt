package com.bemyplan.auth.adapter.out.infra

import com.bemyplan.auth.application.port.out.SaveUserPort
import com.deploy.bemyplan.domain.user.User
import com.deploy.bemyplan.domain.user.UserRepository
import org.springframework.stereotype.Component

@Component
internal class SaveUserAdapter(
    private val userRepository: UserRepository,
): SaveUserPort{
    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun delete(user: User) {
        userRepository.delete(user)
    }
}