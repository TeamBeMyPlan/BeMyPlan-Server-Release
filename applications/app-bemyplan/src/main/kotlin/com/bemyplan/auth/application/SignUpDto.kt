package com.bemyplan.auth.application

import com.bemyplan.auth.application.CreateUserDto.Companion.of
import com.deploy.bemyplan.domain.user.UserSocialType
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class SignUpDto(
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

    fun toCreateUserDto(socialId: String): CreateUserDto {
        return of(socialId, socialType, nickname, email)
    }

    companion object {
        fun of(token: String, nickname: String, email: String, socialType: UserSocialType): SignUpDto {
            return SignUpDto(token, nickname, email, socialType)
        }
    }
}
