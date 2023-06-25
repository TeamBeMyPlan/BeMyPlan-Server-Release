package com.deploy.bemyplan.auth.service.impl;

import com.bemyplan.auth.application.LoginCommand;
import com.bemyplan.auth.application.SignUpCommand;
import com.bemyplan.auth.application.UserService;
import com.deploy.bemyplan.auth.remote.apple.AppleTokenDecoder;
import com.deploy.bemyplan.auth.service.AuthService;
import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.APPLE;

    private final AppleTokenDecoder appleTokenDecoder;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long signUp(SignUpCommand request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return userService.registerUser(request.toCreateUserDto(socialId));
    }

    @Override
    public Long login(LoginCommand request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        User user = userRepository.findUserBySocialIdAndSocialType(socialId, socialType);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s - %s) 입니다", socialId, socialType));
        }
        return user.getId();
    }
}