package com.bemyplan.auth.application.port.`in`

import com.bemyplan.auth.domain.UserDomain

interface ReadUserUsecase {
    fun getUserById(userId: Long): UserDomain
    fun checkIsAvailableName(command: CheckAvailableNameCommand)
}