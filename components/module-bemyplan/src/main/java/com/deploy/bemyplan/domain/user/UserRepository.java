package com.deploy.bemyplan.domain.user;

import com.deploy.bemyplan.domain.user.repository.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<Long> findCreatorIdByUserId(Long userId);
}