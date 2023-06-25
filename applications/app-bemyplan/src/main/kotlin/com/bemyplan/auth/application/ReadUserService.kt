package com.bemyplan.auth.application

import com.bemyplan.auth.application.port.`in`.CheckAvailableNameCommand
import com.bemyplan.auth.application.port.`in`.ReadUserUsecase
import com.bemyplan.auth.application.port.out.GetUserPort
import com.deploy.bemyplan.common.exception.model.NotFoundException
import com.deploy.bemyplan.domain.user.User
import org.springframework.stereotype.Service

@Service
internal class ReadUserService(
    private val getUserPort: GetUserPort,
) : ReadUserUsecase {
    override fun getUserById(userId: Long): User {
        return getUserPort.findById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
    }

    override fun checkIsAvailableName(command: CheckAvailableNameCommand) {
        validateNotExistsUserName(getUserPort, command.nickname)
    }
}