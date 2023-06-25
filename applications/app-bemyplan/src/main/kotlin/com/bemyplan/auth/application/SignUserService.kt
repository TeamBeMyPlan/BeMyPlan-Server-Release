package com.bemyplan.auth.application

import com.bemyplan.auth.application.port.`in`.SignUserUsecase
import com.deploy.bemyplan.auth.service.AuthService
import com.deploy.bemyplan.auth.service.AuthServiceProvider
import com.deploy.bemyplan.common.exception.model.NotFoundException
import com.deploy.bemyplan.domain.user.User
import com.deploy.bemyplan.domain.user.UserRepository
import com.deploy.bemyplan.domain.user.WithdrawalUser
import com.deploy.bemyplan.domain.user.WithdrawalUserRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SignUserService(
    private val userRepository: UserRepository,
    private val withdrawalUserRepository: WithdrawalUserRepository,
    private val eventPublisher: ApplicationEventPublisher,
    private val authServiceProvider: AuthServiceProvider,
): SignUserUsecase {
    override fun signUp(request: SignUpCommand): Long {
        val authService: AuthService = authServiceProvider.getAuthService(request.socialType)
        val userSocialId = authService.signUp(request)

        validateNotExistsUser(userRepository, userSocialId, request.socialType)
        validateNotExistsUserName(userRepository, request.nickname)
        val user = User.newInstance(userSocialId, request.socialType, request.nickname, request.email)
        userRepository.save(user)
        return user.id
    }

    override fun signIn(request: LoginCommand): User {
        val authService: AuthService = authServiceProvider.getAuthService(request.socialType)
        val userId = authService.login(request)
        return userRepository.findUserById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
    }

    override fun signOut(userId: Long, reasonForWithdrawal: String) {
        val user = userRepository.findUserById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
        user.inactive()
        userRepository.delete(user)
        withdrawalUserRepository.save(WithdrawalUser.newInstance(user, reasonForWithdrawal))
        eventPublisher.publishEvent(UserDeleteEvent(userId))
    }
}