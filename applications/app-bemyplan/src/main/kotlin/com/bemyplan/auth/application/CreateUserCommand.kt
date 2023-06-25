package com.bemyplan.auth.application

import com.deploy.bemyplan.domain.user.UserSocialType
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class CreateUserCommand(
    @NotBlank(message = "{user.socialId.notBlank}")
    val socialId: String,
    @NotNull(message = "{user.socialType.notNull}")
    val socialType: UserSocialType,
    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,15}$", message = "{user.nickname.format}")
    val nickname: String,
    @NotNull(message = "{user.email.notNull")
    @Email(message = "{user.email.format}")
    val email: String,
) {


    companion object {
        fun of(socialId: String, socialType: UserSocialType, name: String, email: String): CreateUserCommand {
            return CreateUserCommand(socialId, socialType, name, email)
        }
    }
}
