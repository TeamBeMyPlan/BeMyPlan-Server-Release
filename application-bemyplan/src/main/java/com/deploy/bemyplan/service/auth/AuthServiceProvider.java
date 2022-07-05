package com.deploy.bemyplan.service.auth;

import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.service.auth.impl.AppleAuthService;
import com.deploy.bemyplan.service.auth.impl.GoogleAuthService;
import com.deploy.bemyplan.service.auth.impl.KaKaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AuthServiceProvider {

    private static final Map<UserSocialType, AuthService> authServiceMap = new HashMap<>();

    private final AppleAuthService appleAuthService;
    private final KaKaoAuthService kaKaoAuthService;
    private final GoogleAuthService googleAuthService;

    @PostConstruct
    void initializeAuthServicesMap() {
        authServiceMap.put(UserSocialType.APPLE, appleAuthService);
        authServiceMap.put(UserSocialType.KAKAO, kaKaoAuthService);
        authServiceMap.put(UserSocialType.GOOGLE, googleAuthService);
    }

    public AuthService getAuthService(UserSocialType socialType) {
        return authServiceMap.get(socialType);
    }
}