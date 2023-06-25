package com.bemyplan.auth

import javax.validation.constraints.NotBlank

data class SignOutUserRequest(
    @NotBlank(message = "{signOut.reason.notBlank}") val reasonForWithdrawal: String
)
