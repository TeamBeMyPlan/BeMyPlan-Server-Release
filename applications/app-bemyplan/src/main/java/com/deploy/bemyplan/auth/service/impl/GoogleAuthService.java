package com.deploy.bemyplan.auth.service.impl;

import com.deploy.bemyplan.auth.service.AuthService;
import com.deploy.bemyplan.auth.service.dto.LoginDto;
import com.deploy.bemyplan.auth.service.dto.SignUpDto;
import com.deploy.bemyplan.common.util.HttpHeaderUtils;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.external.client.google.GoogleAuthApiClient;
import com.deploy.bemyplan.external.client.google.dto.response.GoogleProfileInfoResponse;
import com.deploy.bemyplan.user.service.UserService;
import com.deploy.bemyplan.user.service.UserServiceUtils;
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