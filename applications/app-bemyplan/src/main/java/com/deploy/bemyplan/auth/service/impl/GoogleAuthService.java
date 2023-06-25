package com.deploy.bemyplan.auth.service.impl;

import com.bemyplan.auth.application.port.in.LoginCommand;
import com.bemyplan.auth.application.port.in.SignUpCommand;
import com.deploy.bemyplan.auth.remote.google.GoogleAuthApiClient;
import com.deploy.bemyplan.auth.remote.google.GoogleProfileInfoResponse;
import com.deploy.bemyplan.auth.service.AuthService;
import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoogleAuthService implements AuthService {

    private final UserSocialType socialType = UserSocialType.GOOGLE;

    private final GoogleAuthApiClient googleAuthApiCaller;

    private final UserRepository userRepository;

    @Override
    public String signUp(SignUpCommand request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo(("Bearer " + request.getToken()));
        return response.getId();
    }

    @Override
    public Long login(LoginCommand request) {
        GoogleProfileInfoResponse response = googleAuthApiCaller.getProfileInfo(("Bearer " + request.getToken()));
        String socialId = response.getId();
        User user = userRepository.findUserBySocialIdAndSocialType(socialId, socialType);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s - %s) 입니다", socialId, socialType));
        }
        return user.getId();
    }
}