package com.deploy.bemyplan.auth.application

import com.deploy.bemyplan.auth.application.port.`in`.CheckAvailableNameCommand
import com.deploy.bemyplan.auth.application.port.`in`.ReadUserUsecase
import com.deploy.bemyplan.auth.application.port.out.GetUserPort
import com.deploy.bemyplan.auth.domain.UserDomain
import com.deploy.bemyplan.common.exception.model.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
internal class ReadUserService(
    private val getUserPort: GetUserPort,
) : ReadUserUsecase {
    override fun getUserById(userId: Long): UserDomain {
        return getUserPort.findById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
    }

    override fun checkIsAvailableName(command: CheckAvailableNameCommand) {
        validateNotExistsUserName(getUserPort, command.nickname)
    }
}