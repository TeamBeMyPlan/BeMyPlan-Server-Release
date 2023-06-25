package com.bemyplan.auth

import com.deploy.bemyplan.auth.service.dto.SignUpDto
import com.deploy.bemyplan.domain.user.UserSocialType
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class SignUpRequestDto(
    @NotBlank(message = "{auth.token.notBlank}")
    val token:  String,
    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9._]{1,15}$", message = "{user.nickname.format}")
    val nickname: String,
    @NotNull(message = "{user.email.notNull")
    @Email(message = "{user.email.format}")
    val email: String,
    @NotNull(message = "{user.socialType.notNull}")
    val socialType: UserSocialType,
) {

    fun toServiceDto(): SignUpDto {
        return SignUpDto.of(token, nickname, email, socialType)
    }
}