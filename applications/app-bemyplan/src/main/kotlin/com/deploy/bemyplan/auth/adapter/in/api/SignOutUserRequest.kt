package com.deploy.bemyplan.auth.adapter.`in`.api

import javax.validation.constraints.NotBlank

data class SignOutUserRequest(
    @NotBlank(message = "{signOut.reason.notBlank}") val reasonForWithdrawal: String
)
