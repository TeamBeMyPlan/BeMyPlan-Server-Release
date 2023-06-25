package com.bemyplan.auth.application.port.`in`

import com.bemyplan.auth.application.LoginCommand
import com.bemyplan.auth.application.SignUpCommand
import com.deploy.bemyplan.domain.user.User

interface SignUserUsecase {
    fun signUp(request: SignUpCommand): Long
    fun signIn(request: LoginCommand): User
    fun signOut(userId: Long, reasonForWithdrawal: String)
}