package com.deploy.bemyplan.auth.remote.apple;

import com.deploy.bemyplan.common.exception.model.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

import static com.deploy.bemyplan.common.exception.ErrorCode.VALIDATION_AUTH_TOKEN_EXCEPTION;

@RequiredArgsConstructor
@Component
public class AppleTokenDecoderImpl implements AppleTokenDecoder {

    private final AppleAuthApiClient appleApiCaller;
//    private final AppleAuthProperties appleAuthProperties;
    private final ObjectMapper objectMapper;

    @Override
    public String getSocialIdFromIdToken(@NotNull String idToken) {
        String headerIdToken = idToken.split("\\.")[0];
        try {
            Map<String, String> header = objectMapper.readValue(new String(Base64.getDecoder().decode(headerIdToken), StandardCharsets.UTF_8), new TypeReference<>() {
            });
            PublicKey publicKey = getPublicKey(header);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
//                    .requireIssuer(appleAuthProperties.getIssuer())
//                    .requireAudience(appleAuthProperties.getClientId())
                    .build()
                    .parseClaimsJws(idToken)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            throw new ValidationException(String.format("만료된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.getMessage()), VALIDATION_AUTH_TOKEN_EXCEPTION);
        } catch (JsonProcessingException | InvalidKeySpecException | InvalidClaimException | NoSuchAlgorithmException | IllegalArgumentException e) {
            throw new ValidationException(String.format("잘못된 애플 idToken (%s) 입니다 (reason: %s)", idToken, e.getMessage()), VALIDATION_AUTH_TOKEN_EXCEPTION);
        }
    }

    private PublicKey getPublicKey(Map<String, String> header) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ApplePublicKeyResponse response = appleApiCaller.getAppleAuthPublicKey();
        ApplePublicKeyResponse.Key key = response.getMatchedPublicKey(header.get("kid"), header.get("alg"));

        byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
        byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());

        BigInteger n = new BigInteger(1, nBytes);
        BigInteger e = new BigInteger(1, eBytes);

        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
        KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
        return keyFactory.generatePublic(publicKeySpec);
    }
}
