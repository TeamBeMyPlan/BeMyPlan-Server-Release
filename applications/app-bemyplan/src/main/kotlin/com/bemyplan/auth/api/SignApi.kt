package com.bemyplan.auth.api

import com.bemyplan.auth.application.CheckAvailableNameCommand
import com.bemyplan.auth.application.port.`in`.ReadUserUsecase
import com.bemyplan.auth.application.port.`in`.SignUserUsecase
import com.deploy.bemyplan.common.controller.ResponseDTO
import com.deploy.bemyplan.config.auth.Auth
import com.deploy.bemyplan.config.auth.UserId
import com.deploy.bemyplan.jwt.JwtService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class SignApi(
    private val jwtService: JwtService,
    private val signUserUsecase: SignUserUsecase,
    private val readUserUsecase: ReadUserUsecase,
) {

    @PostMapping("/api/signup")
    fun signUp(@Valid @RequestBody request: SignUpRequest): LoginResponse {
        val userId = signUserUsecase.signUp(request.toServiceDto())
        val token = jwtService.issuedToken(userId.toString(), "USER", 60 * 60 * 24 * 30)

        return LoginResponse.of(token, "never used session id", userId, request.nickname)
    }

    @PostMapping("/api/login")
    fun signIn(@Valid @RequestBody request: LoginRequest): LoginResponse {
        val user = signUserUsecase.signIn(request.toServiceDto())
        val token = jwtService.issuedToken(user.id.toString(), "USER", 60 * 60 * 24 * 30)

        return LoginResponse.of(token, "never used session id", user.id, user.nickname)
    }

    @Auth
    @DeleteMapping("/api/signout")
    fun signOut(@Valid @RequestBody request: SignOutUserRequest,
                @UserId userId: Long): ResponseDTO {
        signUserUsecase.signOut(userId, request.reasonForWithdrawal)
        return ResponseDTO.of("회원탈퇴 성공")
    }

    @GetMapping("/api/user/name/check")
    fun checkAvailableName(@Valid request: CheckAvailableNameCommand): ResponseDTO {
        readUserUsecase.checkIsAvailableName(request)
        return ResponseDTO.of("사용 가능한 닉네임 입니다.")
    }
}