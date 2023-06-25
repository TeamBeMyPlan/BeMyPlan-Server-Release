package com.bemyplan.auth.adapter.out.oauth.apple

import com.deploy.bemyplan.common.exception.model.ValidationException

data class ApplePublicKeyResponse(
    private val keys: List<Key>,
) {
    fun getMatchedPublicKey(kid: String, alg: String): Key {
        return keys.stream()
            .filter { key: Key -> key.kid == kid && key.alg == alg }
            .findFirst()
            .orElseThrow<ValidationException> { ValidationException("일치하는 Public Key가 존재하지 않습니다") }
    }

    data class Key(
        val alg: String,
        val e: String,
        val kid: String,
        val kty: String,
        val n: String,
        val use: String,
    )
}
