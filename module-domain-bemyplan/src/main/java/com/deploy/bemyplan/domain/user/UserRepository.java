package com.deploy.bemyplan.domain.user;

import com.deploy.bemyplan.domain.user.repository.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    @Query("select u from User u where u.id = (select p.userId from Plan p where p.id = :planId)")
    Optional<User> findUserByPlanId(Long planId);
}