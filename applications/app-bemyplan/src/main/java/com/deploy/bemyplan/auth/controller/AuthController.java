package com.deploy.bemyplan.auth.controller;

import com.deploy.bemyplan.auth.controller.dto.request.LoginRequestDto;
import com.deploy.bemyplan.auth.controller.dto.request.SignOutUserRequest;
import com.deploy.bemyplan.auth.controller.dto.request.SignUpRequestDto;
import com.deploy.bemyplan.auth.controller.dto.response.LoginResponse;
import com.deploy.bemyplan.auth.service.AuthService;
import com.deploy.bemyplan.auth.service.AuthServiceProvider;
import com.deploy.bemyplan.common.controller.ResponseDTO;
import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.config.auth.Auth;
import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.jwt.JwtService;
import com.deploy.bemyplan.user.service.UserService;
import com.deploy.bemyplan.user.service.dto.request.CheckAvailableNameRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthServiceProvider authServiceProvider;

    @ApiOperation("회원가입 페이지 - 회원가입을 요청합니다")
    @PostMapping("/v1/signup")
    public LoginResponse signUp(@Valid @RequestBody final SignUpRequestDto request) {
        final AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        final Long userId = authService.signUp(request.toServiceDto());

        final String token = jwtService.issuedToken(String.valueOf(userId), "USER", 60 * 60 * 24 * 30L);

        return LoginResponse.of(token, "never used session id", userId, request.getNickname());
    }

    @ApiOperation("로그인 페이지 - 로그인을 요청합니다")
    @PostMapping("/v1/login")
    public LoginResponse login(@Valid @RequestBody final LoginRequestDto request) {
        final AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        final Long userId = authService.login(request.toServiceDto());

        final String token = jwtService.issuedToken(String.valueOf(userId), "USER", 60 * 60 * 24 * 30L);

        User findUser = userRepository.findUserById(userId);
        if (findUser == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", userId));
        }

        return LoginResponse.of(token, "never used session id", userId, findUser.getNickname());
    }

    @ApiOperation("[인증] 회원탈퇴를 요청합니다")
    @Auth
    @DeleteMapping("/v1/signout")
    public ResponseDTO signOut(@Valid @RequestBody final SignOutUserRequest request, @UserId final Long userId) {
        userService.signOut(userId, request.getReasonForWithdrawal());
        return ResponseDTO.of("회원탈퇴 성공");
    }

    @ApiOperation("회원가입 시 닉네임 중복 여부를 요청합니다. (중복된 닉네임 409 or 사용 불가능한 닉네임 400)" )
    @GetMapping("/v1/user/name/check")
    public ResponseDTO checkAvailableName(@Valid final CheckAvailableNameRequestDto request){
        userService.checkIsAvailableName(request);
        return ResponseDTO.of("사용 가능한 닉네임 입니다.");
    }
}