package com.deploy.bemyplan.external.client.apple.social.dto.response;

import com.deploy.bemyplan.common.exception.model.ValidationException;
import lombok.ToString;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplePublicKeyResponse {

    private List<Key> keys;

    public Key getMatchedPublicKey(String kid, String alg) {
        return keys.stream()
                .filter(key -> key.getKid().equals(kid) && key.getAlg().equals(alg))
                .findFirst()
                .orElseThrow(() -> new ValidationException("일치하는 Public Key가 존재하지 않습니다"));
    }

    @ToString
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Key {
        private String alg;
        private String e;
        private String kid;
        private String kty;
        private String n;
        private String use;
    }
}
