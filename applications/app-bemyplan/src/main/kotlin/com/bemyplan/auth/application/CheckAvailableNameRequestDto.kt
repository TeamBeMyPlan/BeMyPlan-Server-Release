package com.bemyplan.auth.application

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class CheckAvailableNameRequestDto(
    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9._]{1,15}$", message = "{user.nickname.format}")
    val nickname: String,
)
