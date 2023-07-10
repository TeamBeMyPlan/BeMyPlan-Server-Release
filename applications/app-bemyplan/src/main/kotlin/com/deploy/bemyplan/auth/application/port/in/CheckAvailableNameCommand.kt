package com.deploy.bemyplan.auth.application.port.`in`

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class CheckAvailableNameCommand(
    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9._]{1,15}$", message = "{user.nickname.format}")
    val nickname: String,
)
