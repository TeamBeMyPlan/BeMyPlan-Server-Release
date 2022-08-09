package com.deploy.bemyplan.domain.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreviewRepository extends JpaRepository<Preview, Long> {
    Optional<Preview> findPreviewByPlanId(Long planId);
}
