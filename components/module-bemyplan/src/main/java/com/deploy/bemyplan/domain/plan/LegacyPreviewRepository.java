package com.deploy.bemyplan.domain.plan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegacyPreviewRepository extends JpaRepository<PreviewContent, Long> {
    void deleteByPlanId(Long planId);
}
