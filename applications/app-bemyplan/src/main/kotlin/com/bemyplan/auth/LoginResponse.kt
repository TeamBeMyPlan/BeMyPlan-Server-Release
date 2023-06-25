package com.bemyplan.auth

data class LoginResponse(
    val token: String,
    val sessionId: String,
    val userId: Long,
    val nickname: String,
) {
    companion object {
        fun of(token: String, sessionId: String, userId: Long, nickname: String): LoginResponse {
            return LoginResponse(token, sessionId, userId, nickname)
        }
    }
}