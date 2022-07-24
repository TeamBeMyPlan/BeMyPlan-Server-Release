package com.deploy.bemyplan.service.user;

import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.domain.user.WithdrawalUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    private UserService userService;
    private ApplicationEventPublisher spyEventPublisher;
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        spyEventPublisher = mock(ApplicationEventPublisher.class);
        mockUserRepository = mock(UserRepository.class);
        given(mockUserRepository.findUserById(any()))
                .willReturn(User.newInstance("tempId", UserSocialType.APPLE, "tempNickName", "tempEmail"));

        userService = new UserService(mockUserRepository,
                mock(WithdrawalUserRepository.class),
                spyEventPublisher);
    }

    @Test
    @DisplayName("유저 탈퇴시 이벤트 발행 확인")
    void delete_passesUserDeleteEventToEventPublisher() {

        UserDeleteEvent expected = new UserDeleteEvent(userService, 1L);

        userService.signOut(1L, "reason");

        verify(spyEventPublisher, times(1)).publishEvent(expected);
    }

    @Test
    @DisplayName("유저 탈퇴시 soft delete")
    void signOut_changeUserStatusInactive() {
        User user = User.newInstance("socialId", UserSocialType.APPLE, "name", "email");

        userService.signOut(user.getId(), "reason");

        User savedUser = mockUserRepository.findUserById(user.getId());
        assertThat(savedUser.getStatus()).isFalse();
    }
}