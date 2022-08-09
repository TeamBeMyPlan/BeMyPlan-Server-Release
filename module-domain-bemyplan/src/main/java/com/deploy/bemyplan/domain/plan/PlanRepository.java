package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.plan.repository.PlanRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long>, PlanRepositoryCustom {
    Optional<Plan> findById(Long id);
}
