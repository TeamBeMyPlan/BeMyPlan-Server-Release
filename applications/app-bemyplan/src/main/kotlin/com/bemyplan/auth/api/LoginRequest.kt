package com.bemyplan.auth.api

import com.bemyplan.auth.application.LoginCommand
import com.deploy.bemyplan.domain.user.UserSocialType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class LoginRequest(
    @NotBlank(message = "{auth.token.notBlank}")
    val token: String,
    @NotNull(message = "{user.socialType.notNull}")
    val socialType: UserSocialType,
) {
    fun toServiceDto(): LoginCommand {
        return LoginCommand.of(token, socialType)
    }
}
