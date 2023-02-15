package com.deploy.bemyplan.domain.scrap.repository;

import com.deploy.bemyplan.domain.scrap.Scrap;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ScrapRepositoryCustom {
    List<Scrap> findAllByIds(List<Long> scrapIds);
    Scrap findActiveByUserIdAndPlanId(Long planId, @Nullable Long userId);
}