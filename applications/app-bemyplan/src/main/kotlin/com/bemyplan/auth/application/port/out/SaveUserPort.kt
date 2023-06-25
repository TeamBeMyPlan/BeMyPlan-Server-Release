package com.bemyplan.auth.application.port.out

import com.deploy.bemyplan.domain.user.User

interface SaveUserPort {

    fun save(user: User)
    fun delete(user: User)
}