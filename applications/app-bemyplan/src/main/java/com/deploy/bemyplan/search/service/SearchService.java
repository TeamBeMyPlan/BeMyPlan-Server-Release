package com.deploy.bemyplan.search.service;


import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.service.dto.response.PlanSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SearchService {

    private final PlanRepository planRepository;
    private final CreatorRepository creatorRepository;
    private final ScrapRepository scrapRepository;

    private boolean isScraped(final Long userId, final Long planId) {
        return null != userId && scrapRepository.existsScrapByUserIdAndPlanId(userId, planId);
    }
    private Creator getAuthorByPlan(final Plan plan) {
        return creatorRepository.findById(plan.getCreatorId())
                .orElseThrow(() -> new NotFoundException("크리에이터 정보가 존재하지 않습니다."));
    }

    public List<PlanSearchResponse> getPlansSearch(final Long userId, final String search) {
        final List<Plan> findPlans = planRepository.findBySearchKeyword(search);
        return findPlans.stream()
                .map(plan -> PlanSearchResponse.of(
                        plan,
                        getAuthorByPlan(plan),
                        isScraped(userId, plan.getId())
                )).collect(Collectors.toList());
    }
}
