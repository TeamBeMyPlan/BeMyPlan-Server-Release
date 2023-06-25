package com.bemyplan.auth.api

import com.deploy.bemyplan.auth.service.dto.LoginDto
import com.deploy.bemyplan.domain.user.UserSocialType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class LoginRequestDto(
    @NotBlank(message = "{auth.token.notBlank}") val token: String,
    @NotNull(message = "{user.socialType.notNull}") val socialType: UserSocialType,
) {
    fun toServiceDto(): LoginDto {
        return LoginDto.of(token, socialType)
    }
}
