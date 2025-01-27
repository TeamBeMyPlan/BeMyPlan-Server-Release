package com.deploy.bemyplan.domain.scrap;

import com.deploy.bemyplan.domain.scrap.repository.ScrapRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long>, ScrapRepositoryCustom {
    void deleteByUserIdAndPlanId(Long userId, Long planId);

    void deleteByUserId(Long userId);

    Optional<Scrap> findByPlanIdAndUserId(Long userId, Long planId);

    boolean existsScrapByUserIdAndPlanId(Long userId, Long planId);

    List<Scrap> findAllByUserIdAndPlanIdIn(Long userId, List<Long> planIds);
}