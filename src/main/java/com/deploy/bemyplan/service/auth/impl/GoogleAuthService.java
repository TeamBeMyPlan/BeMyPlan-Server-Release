package com.deploy.bemyplan.service.auth.impl;

import com.deploy.bemyplan.common.util.HttpHeaderUtils;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.external.client.google.GoogleAuthApiClient;
import com.deploy.bemyplan.external.client.google.dto.response.GoogleProfileInfoResponse;
import com.deploy.bemyplan.service.auth.AuthService;
import com.deploy.bemyplan.service.auth.dto.request.SignUpDto;
import com.deploy.bemyplan.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoogleAuthService implements AuthService {

    private final UserSocialType socialType = UserSocialType.GOOGLE;

    private final GoogleAuthApiClient googleAuthApiCaller;

    private final UserService userService;

    @Override
    public Long signUp(SignUpDto request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo((HttpHeaderUtils.withBearerToken(request.getToken())));
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }
}