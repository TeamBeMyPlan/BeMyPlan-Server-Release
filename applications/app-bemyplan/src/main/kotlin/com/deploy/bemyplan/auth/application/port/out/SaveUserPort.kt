package com.deploy.bemyplan.auth.application.port.out

import com.deploy.bemyplan.auth.domain.UserDomain


interface SaveUserPort {

    fun save(user: UserDomain): UserDomain
    fun delete(user: UserDomain, reasonForWithdrawal: String)
}