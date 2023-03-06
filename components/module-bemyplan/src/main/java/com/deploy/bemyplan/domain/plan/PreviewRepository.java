package com.deploy.bemyplan.domain.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreviewRepository extends JpaRepository<Preview, Long> {
    List<Preview> findAllPreviewByPlanId(Long planId);
    void deleteByPlanId(Long planId);

    void deleteBySpotId(Long spotId);
}
