package com.bemyplan.auth.application

import com.deploy.bemyplan.common.exception.model.ConflictException
import com.deploy.bemyplan.domain.user.UserRepository
import com.deploy.bemyplan.domain.user.UserSocialType

internal fun validateNotExistsUserName(userRepository: UserRepository, name: String) {
    if (userRepository.existsByName(name)) {
        throw ConflictException("이미 존재하는 닉네임 ${name} 입니다")
    }
}

internal fun validateNotExistsUser(userRepository: UserRepository, socialId: String, socialType: UserSocialType) {
    if (userRepository.existsBySocialIdAndSocialType(socialId, socialType)) {
        throw ConflictException("이미 존재하는 유저 (${socialId} - ${socialType}) 입니다")
    }
}
