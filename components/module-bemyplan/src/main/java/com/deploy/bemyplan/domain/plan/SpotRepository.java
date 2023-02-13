package com.deploy.bemyplan.domain.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> findAllByPlanId(Long planId);
}
