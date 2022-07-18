package com.deploy.bemyplan.domain.scrap;

import com.deploy.bemyplan.domain.scrap.repository.ScrapRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom {
    void deleteByUserIdAndPlanId(Long userId, Long planId);
    Optional<Scrap> findByUserIdAndPlanId(Long userId, Long planId);
}
