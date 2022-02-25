package com.deploy.bemyplan.service.auth.impl;

import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.external.client.apple.AppleTokenDecoder;
import com.deploy.bemyplan.service.auth.AuthService;
import com.deploy.bemyplan.service.auth.dto.request.SignUpDto;
import com.deploy.bemyplan.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.APPLE;

    private final AppleTokenDecoder appleTokenDecoder;

    private final UserService userService;

    @Override
    public Long signUp(SignUpDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return userService.registerUser(request.toCreateUserDto(socialId);
    }
}