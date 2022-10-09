package com.deploy.bemyplan.domain.scrap;

import com.deploy.bemyplan.domain.scrap.repository.ScrapRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom {
    void deleteByUserIdAndPlanId(Long userId, Long planId);

    void deleteByUserId(Long userId);

    Optional<Scrap> findByUserIdAndPlanId(Long userId, Long planId);

    Boolean existsScrapByUserIdAndPlanId(Long userId, Long planId);
}