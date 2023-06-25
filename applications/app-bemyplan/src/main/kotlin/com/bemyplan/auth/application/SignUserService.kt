package com.bemyplan.auth.application

import com.bemyplan.auth.application.port.`in`.LoginCommand
import com.bemyplan.auth.application.port.`in`.SignUpCommand
import com.bemyplan.auth.application.port.`in`.SignUserUsecase
import com.bemyplan.auth.application.port.out.GetSocialIdPort
import com.bemyplan.auth.application.port.out.GetSocialIdQuery
import com.bemyplan.auth.application.port.out.GetUserPort
import com.bemyplan.auth.application.port.out.SaveUserPort
import com.deploy.bemyplan.common.exception.model.NotFoundException
import com.deploy.bemyplan.domain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
internal class SignUserService(
    private val getUserPort: GetUserPort,
    private val saveUserPort: SaveUserPort,
    private val getSocialIdPort: GetSocialIdPort,
): SignUserUsecase {
    override fun signUp(command: SignUpCommand): Long {
        val userSocialId = getSocialIdPort.getSocialId(GetSocialIdQuery(
            command.token,
            command.socialType
        ))

        validateNotExistsUser(getUserPort, userSocialId, command.socialType)
        validateNotExistsUserName(getUserPort, command.nickname)
        val user = User.newInstance(userSocialId, command.socialType, command.nickname, command.email)
        saveUserPort.save(user)
        return user.id
    }

    override fun signIn(command: LoginCommand): User {
        val userSocialId = getSocialIdPort.getSocialId(GetSocialIdQuery(
            command.token,
            command.socialType
        ))

        val user = getUserPort.findBySocialIdAndSocialType(userSocialId, command.socialType)
            ?: throw NotFoundException("존재하지 않는 유저 (${userSocialId} - ${command.socialType}) 입니다")

        return getUserPort.findById(user.id) ?: throw NotFoundException("존재하지 않는 유저 ${user.id} 입니다")
    }

    override fun signOut(userId: Long, reasonForWithdrawal: String) {
        val user = getUserPort.findById(userId) ?: throw NotFoundException("존재하지 않는 유저 ${userId} 입니다")
        user.inactive()
        saveUserPort.delete(user, reasonForWithdrawal)
    }
}