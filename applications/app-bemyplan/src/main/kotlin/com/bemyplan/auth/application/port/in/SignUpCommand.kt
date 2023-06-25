package com.bemyplan.auth.application.port.`in`

import com.deploy.bemyplan.domain.user.UserSocialType
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class SignUpCommand(
    @NotBlank(message = "{auth.token.notBlank}")
    val token: String,
    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,15}$", message = "{user.nickname.format}")
    val nickname: String,
    @NotNull(message = "{user.email.notNull")
    @Email(message = "{user.email.format}")
    val email: String,
    @NotNull(message = "{user.socialType.notNull}")
    val socialType: UserSocialType,
) {

    companion object {
        fun of(token: String, nickname: String, email: String, socialType: UserSocialType): SignUpCommand {
            return SignUpCommand(token, nickname, email, socialType)
        }
    }
}
