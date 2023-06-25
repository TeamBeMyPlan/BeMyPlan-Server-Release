package com.bemyplan.auth.application.port.`in`

import com.bemyplan.auth.domain.UserDomain

interface SignUserUsecase {
    fun signUp(command: SignUpCommand): Long
    fun signIn(command: LoginCommand): UserDomain
    fun signOut(userId: Long, reasonForWithdrawal: String)
}