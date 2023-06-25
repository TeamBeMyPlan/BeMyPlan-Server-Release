package com.bemyplan.auth.adapter.out.oauth

interface AuthService {
    fun getSocialId(token: String): String
}