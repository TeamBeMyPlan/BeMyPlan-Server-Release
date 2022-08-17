package com.deploy.bemyplan.service.user;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.WithdrawalUser;
import com.deploy.bemyplan.domain.user.WithdrawalUserRepository;
import com.deploy.bemyplan.service.user.dto.request.CheckAvailableNameRequestDto;
import com.deploy.bemyplan.service.user.dto.request.CreateUserDto;
import com.deploy.bemyplan.service.user.dto.response.CreatorInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.deploy.bemyplan.common.exception.ErrorCode.NOT_FOUND_PLAN_EXCEPTION;

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
        User user = UserServiceUtils.findUserById(userRepository, userId);
        user.inactive();
        withdrawalUserRepository.save(WithdrawalUser.newInstance(user, reasonForWithdrawal));
        eventPublisher.publishEvent(new UserDeleteEvent(this, userId));
    }

    @Transactional
    public void checkIsAvailableName(CheckAvailableNameRequestDto request) {
        UserServiceUtils.validateNotExistsUserName(userRepository, request.getNickname());
    }
}