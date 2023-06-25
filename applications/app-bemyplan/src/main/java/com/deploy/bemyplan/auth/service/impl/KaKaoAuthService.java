package com.deploy.bemyplan.auth.service.impl;

import com.bemyplan.auth.application.port.in.LoginCommand;
import com.bemyplan.auth.application.port.in.SignUpCommand;
import com.deploy.bemyplan.auth.remote.kakao.KaKaoAuthApiClient;
import com.deploy.bemyplan.auth.remote.kakao.KaKaoProfileResponse;
import com.deploy.bemyplan.auth.service.AuthService;
import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KaKaoAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.KAKAO;

    private final KaKaoAuthApiClient kakaoAuthApiCaller;

    private final UserRepository userRepository;

    @Override
    public String signUp(SignUpCommand request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo("Bearer " + request.getToken());
        return response.getId();
    }

    @Override
    public Long login(LoginCommand request) {
        KaKaoProfileResponse response = kakaoAuthApiCaller.getProfileInfo("Bearer " + request.getToken());
        String socialId = response.getId();
        User user = userRepository.findUserBySocialIdAndSocialType(socialId, socialType);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s - %s) 입니다", socialId, socialType));
        }
        return user.getId();
    }
}