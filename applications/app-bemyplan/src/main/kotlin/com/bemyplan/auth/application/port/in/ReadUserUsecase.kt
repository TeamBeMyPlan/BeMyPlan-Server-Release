package com.bemyplan.auth.application.port.`in`

import com.deploy.bemyplan.domain.user.User

interface ReadUserUsecase {
    fun getUserById(userId: Long): User
    fun checkIsAvailableName(command: CheckAvailableNameCommand)
}