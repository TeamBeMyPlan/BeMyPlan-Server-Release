package com.bemyplan.auth.application

import com.bemyplan.auth.application.port.`in`.ReadUserUsecase
import com.deploy.bemyplan.common.exception.model.NotFoundException
import com.deploy.bemyplan.domain.user.User
import com.deploy.bemyplan.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class ReadUserService(
    private val userRepository: UserRepository,
) : ReadUserUsecase {
    override fun getUserById(userId: Long): User {
        return userRepository.findUserById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
    }

    override fun checkIsAvailableName(request: CheckAvailableNameCommand) {
        validateNotExistsUserName(userRepository, request.nickname)
    }
}