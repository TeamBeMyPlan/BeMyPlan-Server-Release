package com.deploy.bemyplan.service.user;

import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.WithdrawalUser;
import com.deploy.bemyplan.domain.user.WithdrawalUserRepository;
import com.deploy.bemyplan.service.user.dto.request.CheckAvailableNameRequestDto;
import com.deploy.bemyplan.service.user.dto.request.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final WithdrawalUserRepository withdrawalUserRepository;

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
        User user = UserServiceUtils.findUserById(userRepository, userId);
        withdrawalUserRepository.save(WithdrawalUser.newInstance(user, reasonForWithdrawal));
        userRepository.delete(user);
    }

    @Transactional
    public void checkIsAvailableName(CheckAvailableNameRequestDto request) {
        UserServiceUtils.validateNotExistsUserName(userRepository, request.getNickname());
    }
}