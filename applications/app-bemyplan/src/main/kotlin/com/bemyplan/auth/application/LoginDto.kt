package com.bemyplan.auth.application

import com.deploy.bemyplan.domain.user.UserSocialType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class LoginDto(
    @NotBlank(message = "{auth.token.notBlank}")
    val token: String,
    @NotNull(message = "{user.socialType.notNull}")
    val socialType: UserSocialType
){
    companion object {
        fun of(token: String, socialType: UserSocialType): LoginDto {
            return LoginDto(token, socialType)
        }
    }
}
