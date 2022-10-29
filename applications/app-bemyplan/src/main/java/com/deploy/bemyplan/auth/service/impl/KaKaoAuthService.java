package com.deploy.bemyplan.auth.service.impl;

import com.deploy.bemyplan.auth.service.AuthService;
import com.deploy.bemyplan.auth.service.dto.LoginDto;
import com.deploy.bemyplan.auth.service.dto.SignUpDto;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.external.client.kakao.KaKaoAuthApiClient;
import com.deploy.bemyplan.external.client.kakao.dto.response.KaKaoProfileResponse;
import com.deploy.bemyplan.user.service.UserService;
import com.deploy.bemyplan.user.service.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KaKaoAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.KAKAO;

    private final KaKaoAuthApiClient kakaoAuthApiCaller;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long signUp(SignUpDto request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo("Bearer " + request.getToken());
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }

    @Override
    public Long login(LoginDto request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo("Bearer " + request.getToken());
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType).getId();
    }
}