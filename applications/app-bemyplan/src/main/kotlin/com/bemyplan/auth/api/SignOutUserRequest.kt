package com.bemyplan.auth.api

import javax.validation.constraints.NotBlank

data class SignOutUserRequest(
    @NotBlank(message = "{signOut.reason.notBlank}") val reasonForWithdrawal: String
)
