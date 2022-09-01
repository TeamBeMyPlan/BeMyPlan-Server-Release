package com.deploy.bemyplan.domain.user.repository;

import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class UserRepositoryCustomImplTest {
    private UserRepositoryCustomImpl userRepositoryCustom;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepositoryCustom = new UserRepositoryCustomImpl(jpaQueryFactory);
    }

    @Test
    void existByName() {
        User user = User.newInstance("socialId", UserSocialType.APPLE, "name", "email");
        userRepository.save(user);

        assertThat(userRepositoryCustom.existsByName("name")).isTrue();
    }

    @Test
    void existsBySocialIdAndSocialType() {
        User user = User.newInstance("socialId", UserSocialType.APPLE, "name", "email");
        userRepository.save(user);

        assertThat(userRepositoryCustom.existsBySocialIdAndSocialType(user.getSocialId(),user.getSocialType())).isTrue();
    }

    @Test
    void findUserBySocialIdAndSocialType() {
        User user = User.newInstance("socialId", UserSocialType.APPLE, "name", "email");
        User user2 = User.newInstance("socialId2", UserSocialType.APPLE, "name", "email");
        userRepository.save(user);
        userRepository.save(user2);

        assertThat(userRepositoryCustom.findUserBySocialIdAndSocialType("socialId", UserSocialType.APPLE)).isEqualTo(user);
    }

    @Test
    void findUserBySocialIdAndSocialType_fail_whenUserStatusIsInactive() {
        User user = User.newInstance("socialId", UserSocialType.APPLE, "name", "email");
        user.inactive();
        userRepository.save(user);

        assertThat(userRepositoryCustom.findUserBySocialIdAndSocialType("socialId", UserSocialType.APPLE)).isNull();
    }
}