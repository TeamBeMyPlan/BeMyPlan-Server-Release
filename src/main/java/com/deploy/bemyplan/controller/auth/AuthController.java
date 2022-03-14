package com.deploy.bemyplan.controller.auth;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.controller.auth.dto.request.LoginRequestDto;
import com.deploy.bemyplan.controller.auth.dto.request.SignUpRequestDto;
import com.deploy.bemyplan.service.auth.AuthService;
import com.deploy.bemyplan.service.auth.AuthServiceProvider;
import com.deploy.bemyplan.controller.auth.dto.response.LoginResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.deploy.bemyplan.config.session.SessionConstants.USER_ID;


@RequiredArgsConstructor
@RestController
public class AuthController {

    private final HttpSession httpSession;
    private final AuthServiceProvider authServiceProvider;

    @ApiOperation("회원가입 페이지 - 회원가입을 요청합니다")
    @PostMapping("/v1/signup")
    public ApiResponse<LoginResponse> signUp(@Valid @RequestBody SignUpRequestDto request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        Long userId = authService.signUp(request.toServiceDto());
        httpSession.setAttribute(USER_ID, userId);
        return ApiResponse.success(LoginResponse.of(httpSession.getId(), userId));
    }

    @ApiOperation("로그인 페이지 - 로그인을 요청합니다")
    @PostMapping("/v1/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequestDto request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        Long userId = authService.login(request.toServiceDto());
        httpSession.setAttribute(USER_ID, userId);
        return ApiResponse.success(LoginResponse.of(httpSession.getId(), userId));
    }

    @ApiOperation("[인증] 로그아웃을 요청합니다.")
    @Auth
    @PostMapping("/v1/logout")
    public ApiResponse<String> logout() {
        httpSession.removeAttribute(USER_ID);
        return ApiResponse.SUCCESS;
    }
}