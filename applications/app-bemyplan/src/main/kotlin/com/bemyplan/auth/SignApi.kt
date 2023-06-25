package com.bemyplan.auth

import com.deploy.bemyplan.auth.service.AuthService
import com.deploy.bemyplan.auth.service.AuthServiceProvider
import com.deploy.bemyplan.common.controller.ResponseDTO
import com.deploy.bemyplan.config.auth.Auth
import com.deploy.bemyplan.config.auth.UserId
import com.deploy.bemyplan.jwt.JwtService
import com.deploy.bemyplan.user.service.UserService
import com.deploy.bemyplan.user.service.dto.request.CheckAvailableNameRequestDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class SignApi(
    private val userService: UserService,
    private val jwtService: JwtService,
    private val authServiceProvider: AuthServiceProvider
) {

    @PostMapping("/api/signup")
    fun signUp(@Valid @RequestBody request: SignUpRequestDto): LoginResponse {
        val authService: AuthService = authServiceProvider.getAuthService(request.socialType)
        val userId = authService.signUp(request.toServiceDto())
        val token = jwtService.issuedToken(userId.toString(), "USER", 60 * 60 * 24 * 30)

        return LoginResponse.of(token, "never used session id", userId, request.nickname)
    }

    @PostMapping("/api/login")
    fun signIn(@Valid @RequestBody request: LoginRequestDto): LoginResponse {
        val authService: AuthService = authServiceProvider.getAuthService(request.socialType)
        val userId = authService.login(request.toServiceDto())
        val token = jwtService.issuedToken(userId.toString(), "USER", 60 * 60 * 24 * 30)
        val user = userService.getUserById(userId)

        return LoginResponse.of(token, "never used session id", userId, user.nickname)
    }

    @Auth
    @DeleteMapping("/api/signout")
    fun signOut(@Valid @RequestBody request: SignOutUserRequest,
                @UserId userId: Long): ResponseDTO {
        userService.signOut(userId, request.reasonForWithdrawal)
        return ResponseDTO.of("회원탈퇴 성공")
    }

    @GetMapping("/api/user/name/check")
    fun checkAvailableName(@Valid request: CheckAvailableNameRequestDto): ResponseDTO {
        userService.checkIsAvailableName(request)
        return ResponseDTO.of("사용 가능한 닉네임 입니다.")
    }
}