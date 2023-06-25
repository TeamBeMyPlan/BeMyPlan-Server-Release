package com.bemyplan.auth.application

import com.deploy.bemyplan.common.exception.model.NotFoundException
import com.deploy.bemyplan.domain.user.User
import com.deploy.bemyplan.domain.user.UserRepository
import com.deploy.bemyplan.domain.user.WithdrawalUser
import com.deploy.bemyplan.domain.user.WithdrawalUserRepository
import com.deploy.bemyplan.user.service.dto.request.CheckAvailableNameRequestDto
import com.deploy.bemyplan.user.service.dto.request.CreateUserDto
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val withdrawalUserRepository: WithdrawalUserRepository,
    private val eventPublisher: ApplicationEventPublisher,
) {

    @Transactional
    fun registerUser(request: CreateUserDto): Long {
        validateNotExistsUser(userRepository, request.socialId, request.socialType)
        validateNotExistsUserName(userRepository, request.nickname)
        val user = User.newInstance(request.socialId, request.socialType, request.nickname, request.email)
        userRepository.save(user)
        return user.id
    }

    @Transactional
    fun signOut(userId: Long, reasonForWithdrawal: String) {
        val user = userRepository.findUserById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
        user.inactive()
        userRepository.delete(user)
        withdrawalUserRepository.save(WithdrawalUser.newInstance(user, reasonForWithdrawal))
        eventPublisher.publishEvent(UserDeleteEvent(userId))
    }

    @Transactional
    fun checkIsAvailableName(request: CheckAvailableNameRequestDto) {
        validateNotExistsUserName(userRepository, request.nickname)
    }

    fun getUserById(userId: Long): User {
        return userRepository.findUserById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
    }
}