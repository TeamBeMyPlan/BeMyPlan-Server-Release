package com.deploy.bemyplan.service.auth.impl;

import com.deploy.bemyplan.common.util.HttpHeaderUtils;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.external.client.google.GoogleAuthApiClient;
import com.deploy.bemyplan.external.client.google.dto.response.GoogleProfileInfoResponse;
import com.deploy.bemyplan.service.auth.AuthService;
import com.deploy.bemyplan.service.auth.dto.request.LoginDto;
import com.deploy.bemyplan.service.auth.dto.request.SignUpDto;
import com.deploy.bemyplan.service.user.UserService;
import com.deploy.bemyplan.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoogleAuthService implements AuthService {

    private final UserSocialType socialType = UserSocialType.GOOGLE;

    private final GoogleAuthApiClient googleAuthApiCaller;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long signUp(SignUpDto request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo((HttpHeaderUtils.withBearerToken(request.getToken())));
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }

    @Override
    public Long login(LoginDto request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo((HttpHeaderUtils.withBearerToken(request.getToken())));
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType).getId();
    }
}