package com.deploy.bemyplan.domain.scrap.repository;

import com.deploy.bemyplan.domain.scrap.Scrap;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ScrapRepositoryCustom {

    List<Scrap> findAllByIds(List<Long> scrapIds);
    List<Scrap> findByUserIdAndPlanIds(List<Long> planIds, @Nullable Long userId);
    Scrap findByUserIdAndPlanId(Long planId, @Nullable Long userId);
    Scrap findActiveByUserIdAndPlanId(Long planId, @Nullable Long userId);
}