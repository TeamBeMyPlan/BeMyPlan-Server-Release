package com.deploy.bemyplan.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserSocialType {

    KAKAO("카카오톡"),
    APPLE("애플"),
    GOOGLE("구글"),
    ;

    private final String value;
}