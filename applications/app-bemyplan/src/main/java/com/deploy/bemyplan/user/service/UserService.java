package com.deploy.bemyplan.user.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.WithdrawalUser;
import com.deploy.bemyplan.domain.user.WithdrawalUserRepository;
import com.deploy.bemyplan.user.service.dto.request.CheckAvailableNameRequestDto;
import com.deploy.bemyplan.user.service.dto.request.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final WithdrawalUserRepository withdrawalUserRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        UserServiceUtils.validateNotExistsUserName(userRepository, request.getNickname());
        User user = User.newInstance(request.getSocialId(), request.getSocialType(), request.getNickname(), request.getEmail());

        userRepository.save(user);

        return user.getId();
    }

    @Transactional
    public void signOut(Long userId, String reasonForWithdrawal) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", userId));
        }
        user.inactive();
        withdrawalUserRepository.save(WithdrawalUser.newInstance(user, reasonForWithdrawal));
        eventPublisher.publishEvent(new UserDeleteEvent(this, userId));
    }

    @Transactional
    public void checkIsAvailableName(CheckAvailableNameRequestDto request) {
        UserServiceUtils.validateNotExistsUserName(userRepository, request.getNickname());
    }
}