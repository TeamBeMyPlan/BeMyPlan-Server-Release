package com.deploy.bemyplan.auth.application.port.`in`

import com.deploy.bemyplan.auth.domain.UserDomain


interface ReadUserUsecase {
    fun getUserById(userId: Long): UserDomain
    fun checkIsAvailableName(command: CheckAvailableNameCommand)
}