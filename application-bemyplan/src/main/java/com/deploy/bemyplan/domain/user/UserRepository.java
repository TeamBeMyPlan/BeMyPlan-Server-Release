package com.deploy.bemyplan.domain.user;

import com.deploy.bemyplan.domain.user.repository.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}