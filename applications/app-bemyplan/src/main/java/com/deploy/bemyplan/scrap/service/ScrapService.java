package com.deploy.bemyplan.scrap.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.order.OrderRepository;
import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.ScrapedPlan;
import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapRepository;
import com.deploy.bemyplan.plan.service.dto.response.PlanScrapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final PlanRepository planRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void addScrap(Long planId, Long userId) {
        scrapRepository.save(Scrap.of(planId, userId));
    }

    @Transactional
    public void deleteScrap(Long userId, Long planId) {
        scrapRepository.deleteByUserIdAndPlanId(userId, planId);
    }

    @Transactional(readOnly = true)
    public void checkScrapStatus(Long planId, Long userId) {
        scrapRepository.findByPlanIdAndUserId(planId, userId)
                .orElseThrow(() -> new NotFoundException(String.format("해당 여행일정을 찜한 상태 (%s - %s) 가 아닙니다.", userId, planId)));
    }

    @Transactional
    public void deleteAllScrapByUser(Long userId) {
        scrapRepository.deleteByUserId(userId);
    }


    public List<PlanScrapResponse> getPlanWithScrap(final Long userId, final String sort) {
        if ("scrapCnt".equals(sort)) {
            return getScrapPlanByScrapCount(userId);
        }
        if ("orderCnt".equals(sort)) {
            return getScrapPlanByOrderCount(userId);
        }
        return getScrapPlanByCreatedAt(userId);
    }


    private List<PlanScrapResponse> getScrapPlanByOrderCount(final Long userId) {
        final List<ScrapedPlan> findPlans = planRepository.findScrapPlanOrderByOrderCount(userId);
        return getPlanScrapResponses(userId, findPlans);
    }

    private List<PlanScrapResponse> getScrapPlanByCreatedAt(final Long userId) {
        final List<ScrapedPlan> findPlans = planRepository.findScrapPlanOrderByCreatedAtDesc(userId);
        return getPlanScrapResponses(userId, findPlans);
    }

    private List<PlanScrapResponse> getScrapPlanByScrapCount(final Long userId) {
        final List<ScrapedPlan> findPlans = planRepository.findScrapPlanOrderByScrapCount(userId);
        return getPlanScrapResponses(userId, findPlans);
    }

    private List<PlanScrapResponse> getPlanScrapResponses(final Long userId, final List<ScrapedPlan> findPlans) {
        return findPlans.stream()
                .map(plan -> PlanScrapResponse.of(
                        plan.getId(),
                        plan.getThumbnailUrl(),
                        plan.getTitle(),
                        isScraped(userId, plan.getId()),
                        isOrdered(userId, plan.getId()),
                        plan.getCreatedAt()))
                .collect(Collectors.toList());
    }


    private boolean isScraped(final Long userId, final Long planId) {
        return null != userId && scrapRepository.existsScrapByUserIdAndPlanId(userId, planId);
    }

    private boolean isOrdered(final Long userId, final Long planId) {
        return null != userId && orderRepository.existsOrderByUserIdAndPlanIdAndStatus(userId, planId, OrderStatus.COMPLETED);
    }

}