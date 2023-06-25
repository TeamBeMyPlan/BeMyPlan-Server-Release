package com.bemyplan.auth.adapter.out.oauth

internal interface AuthService {
    fun getSocialId(token: String): String
}