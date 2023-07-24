package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.service.dto.request.RetrievePlansRequest;
import com.deploy.bemyplan.plan.service.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanInfoResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanListResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanMainInfoResponse;
import com.deploy.bemyplan.plan.service.dto.response.SpotMoveInfoDetailResponse;
import com.deploy.bemyplan.plan.service.dto.response.SpotMoveInfoResponse;
import com.deploy.bemyplan.scrap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlanRetrieveService {
    private final CreatorRepository creatorRepository;
    private final PlanRepository planRepository;
    private final ScrapService scrapService;

    public PlanListResponse retrievePlans(final Long userId, RetrievePlansRequest request) {
        final List<Plan> planList = getPlanListByOrder(request.getRegion(), request.getSort());
        final List<Plan> filteredPlans = planList.stream()
                .filter(plan -> containsPartners(plan, request))
                .collect(Collectors.toList());

        return getPlanListWithPersonalStatus(filteredPlans, userId);
    }

    private boolean containsPartners(final Plan plan, final RetrievePlansRequest request) {
        if (request.getPartners().isEmpty()) {
            return true;
        }

        return request.getPartners().contains(plan.getTagInfo().getPartner());
    }

    public PlanListResponse getPickList(final Long userId) {
        final List<Plan> planList = planRepository.findPickList();
        return getPlanListWithPersonalStatus(planList, userId);
    }

    public PlanDetailResponse getPlanDetailInfo(final Long planId) {
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 일정 (%s) 입니다", planId)));
        final Creator creator = creatorRepository.findById(plan.getCreatorId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 크리에이터입니다."));

        return PlanDetailResponse.of(plan, creator);
    }

    public List<SpotMoveInfoResponse> getSpotMoveInfos(final Long planId) {
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 일정 (%s) 입니다", planId)));

        final List<SpotMoveInfoResponse> result = new ArrayList<>();

        final Map<Integer, List<Spot>> spotsPerDate = plan.getSpots().stream().collect(groupingBy(Spot::getDay));
        for (final int day : spotsPerDate.keySet()) {
            final List<Spot> spots = spotsPerDate.get(day);

            final List<SpotMoveInfoDetailResponse> moveInfos = new ArrayList<>();
            for (int i = 0; i < spots.size() - 1; i++) {
                final Spot prev = spots.get(i);
                final Spot next = spots.get(i + 1);

                moveInfos.add(new SpotMoveInfoDetailResponse(prev.getId(), next.getId(), prev.getVehicle(), prev.getSpentMinute()));
            }

            result.add(new SpotMoveInfoResponse(day, moveInfos));
        }

        return result;
    }

    private List<Plan> getPlanListByOrder(final RegionCategory region, final String sort) {
        if ("scrapCnt".equals(sort)) {
            return planRepository.findAllPlanByRegionCategoryOrderByScrap(region);
        } else if ("orderCnt".equals(sort)) {
            return planRepository.findAllPlanByRegionCategory(region).stream()
                    .sorted(Comparator.comparingInt(Plan::getOrderCnt).reversed())
                    .collect(Collectors.toList());
        }
        return planRepository.findAllPlanByRegionCategory(region);
    }


    private PlanListResponse getPlanListWithPersonalStatus(final List<Plan> planList, final Long userId) {
        return PlanListResponse.of(planList.stream()
                .map(plan -> PlanInfoResponse.of(plan,
                        getAuthorByPlan(plan),
                        scrapService.isScraped(userId, plan.getId()),
                        scrapService.isOrdered(userId, plan.getId())))
                .collect(Collectors.toList()));
    }
    private Creator getAuthorByPlan(final Plan plan) {
        return creatorRepository.findById(plan.getCreatorId())
                .orElseThrow(() -> new NotFoundException("크리에이터 정보가 존재하지 않습니다."));
    }

    public List<PlanMainInfoResponse> getPlansByOrder(final Long userId, final String sort) {
        if ("orderCnt".equals(sort)) {
            final List<Plan> plans = planRepository.findAllByOrderCntDesc();
            return getPlanMainInfoResponses(userId, plans);
        }
        final List<Plan> plans = planRepository.findAllByCreatedAtDesc();
        return getPlanMainInfoResponses(userId, plans);
    }

    private List<PlanMainInfoResponse> getPlanMainInfoResponses(final Long userId, final List<Plan> plans) {
        return plans.stream()
                .map(plan -> PlanMainInfoResponse.of(
                        plan.getId(),
                        plan.getThumbnailUrl(),
                        plan.getTitle(),
                        plan.getRegion(),
                        scrapService.isScraped(userId, plan.getId()),
                        getAuthorByPlan(plan),
                        scrapService.isOrdered(userId, plan.getId()),
                        plan.getCreatedAt()
                )).collect(Collectors.toList());
    }

    public List<PlanMainInfoResponse> getPlansByRegion(final Long userId, final Region region, final String sort) {
        final List<Plan> plans = planRepository.findAllByRegion(region);
        final Comparator<Plan> comparator;

        if ("orderCnt".equals(sort)) {
            comparator = Comparator.comparing(Plan::getOrderCnt).reversed();
        } else {
            comparator = Comparator.comparing(Plan::getCreatedAt).reversed();
        }

        return plans.stream()
                .sorted(comparator)
                .map(plan -> PlanMainInfoResponse.of(
                        plan.getId(),
                        plan.getThumbnailUrl(),
                        plan.getTitle(),
                        plan.getRegion(),
                        scrapService.isScraped(userId, plan.getId()),
                        getAuthorByPlan(plan),
                        scrapService.isOrdered(userId, plan.getId()),
                        plan.getCreatedAt()))
                .collect(Collectors.toList());
    }
}