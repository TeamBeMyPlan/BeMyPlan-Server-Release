package com.deploy.bemyplan.auth.adapter.out.oauth.apple

import com.deploy.bemyplan.common.exception.model.ValidationException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.RSAPublicKeySpec
import java.util.*

@Component
internal class AppleTokenDecoder(
    private val appleApiCaller: AppleAuthApiClient,
    private val objectMapper: ObjectMapper,
) {

    fun getSocialIdFromIdToken(idToken: String): String {
        val headerIdToken = idToken.split("\\.".toRegex())[0]
        try {
            val header: Map<String, String> = objectMapper.readValue(Base64.getDecoder().decode(headerIdToken).toString(StandardCharsets.UTF_8), object : TypeReference<Map<String, String>>() {})
            val publicKey: PublicKey = getPublicKey(header)
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(idToken)
                .body
            return claims.subject
        } catch (e: ExpiredJwtException) {
            throw ValidationException(String.format("만료된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.message))
        } catch (e: JsonProcessingException) {
            throw ValidationException(String.format("잘못된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.message))
        } catch (e: InvalidKeySpecException) {
            throw ValidationException(String.format("잘못된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.message))
        } catch (e: NoSuchAlgorithmException) {
            throw ValidationException(String.format("잘못된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.message))
        } catch (e: IllegalArgumentException) {
            throw ValidationException(String.format("잘못된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.message))
        }
    }

    private fun getPublicKey(header: Map<String, String>): PublicKey {
        val response = appleApiCaller.getAppleAuthPublicKey()
        val key = response.getMatchedPublicKey(header["kid"]!!, header["alg"]!!)
        val nBytes = Base64.getUrlDecoder().decode(key.n)
        val eBytes = Base64.getUrlDecoder().decode(key.e)
        val n = BigInteger(1, nBytes)
        val e = BigInteger(1, eBytes)
        val publicKeySpec = RSAPublicKeySpec(n, e)
        val keyFactory = KeyFactory.getInstance(key.kty)
        return keyFactory.generatePublic(publicKeySpec)
    }
}
