package com.deploy.bemyplan.domain.scrap.repository;

import com.deploy.bemyplan.domain.scrap.Scrap;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScrapRepositoryCustom {

    List<Scrap> findAllByIds(List<Long> scrapIds);
    List<Scrap> findByUserIdAndPlanIds(List<Long> planIds, Long userId);
    Scrap findByUserIdAndPlanId(Long planId, Long userId);
}