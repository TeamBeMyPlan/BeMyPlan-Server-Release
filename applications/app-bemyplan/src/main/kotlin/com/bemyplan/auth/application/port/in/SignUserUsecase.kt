package com.bemyplan.auth.application.port.`in`

import com.deploy.bemyplan.domain.user.User

interface SignUserUsecase {
    fun signUp(command: SignUpCommand): Long
    fun signIn(command: LoginCommand): User
    fun signOut(userId: Long, reasonForWithdrawal: String)
}