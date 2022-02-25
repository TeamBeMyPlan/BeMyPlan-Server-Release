package com.deploy.bemyplan.service.user;

import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.service.user.dto.request.CreateUserDto;
import com.deploy.bemyplan.service.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        UserServiceUtils.validateNotExistsUserName(userRepository, request.getNickname());
        User user = userRepository.save(UserMapper.INSTANCE.createUserDtoToEntity(request));
        return user.getId();
    }
}
