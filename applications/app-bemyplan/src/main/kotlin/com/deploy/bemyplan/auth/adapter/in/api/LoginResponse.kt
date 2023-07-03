package com.deploy.bemyplan.auth.adapter.`in`.api

data class LoginResponse(
    val token: String,
    val sessionId: String,
    val userId: Long,
    val nickname: String,
) {
    companion object {
        fun of(token: String, sessionId: String, userId: Long, nickname: String): com.deploy.bemyplan.auth.adapter.`in`.api.LoginResponse {
            return com.deploy.bemyplan.auth.adapter.`in`.api.LoginResponse(token, sessionId, userId, nickname)
        }
    }
}