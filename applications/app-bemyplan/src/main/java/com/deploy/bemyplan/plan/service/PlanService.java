package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.controller.RetrievePlansRequest;
import com.deploy.bemyplan.plan.service.dto.request.GetPlansByThemeRequest;
import com.deploy.bemyplan.plan.service.dto.response.GetPlanResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanInfoResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanListResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponseDto;
import com.deploy.bemyplan.plan.service.dto.response.PlanRandomResponse;
import com.deploy.bemyplan.scrap.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlanService {
    private final PreviewRepository previewRepository;
    private final PlanRepository planRepository;
    private final CreatorRepository creatorRepository;
    private final ScrapService scrapService;

    public List<PlanRandomResponse> getPlanListByRandom(final Long planId, final RegionCategory region) {
        final int size = 10;
        final List<Plan> plans = planRepository.findPlansByRegionAndSize(planId, region.name(), size);
        return plans.stream()
                .map(p -> PlanRandomResponse.of(p.getId(), p.getThumbnailUrl(), p.getTitle(), p.getRegionCategory(), p.getRegion()))
                .collect(Collectors.toList());
    }

    public PlanPreviewResponseDto getPlanPreview(final Long planId) {
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 일정 (%s) 입니다", planId)));
        final List<Preview> previews = previewRepository.findAllPreviewByPlanId(planId);

        final List<String> previewImages = getPreviewImages(previews);

        return PlanPreviewResponseDto.of(plan, previewImages);
    }

    @NotNull
    private List<String> getPreviewImages(final List<Preview> previews) {
        return previews.stream()
                .map(preview -> getSpotImages(preview.getSpot()).stream().findFirst().orElse(""))
                .collect(Collectors.toList());
    }

    private List<String> getSpotImages(final Spot spot) {
        return spot.getImages().stream()
                .map(SpotImage::getUrl)
                .collect(Collectors.toList());
    }

    public PlanListResponse getPlans(final RetrievePlansRequest request) {
        final var region = request.getRegion();
        final var plansByRegion = planRepository.findAllPlanByRegionCategory(region);
        return PlanListResponse.of(plansByRegion.stream()
                .map(plan -> PlanInfoResponse.of(plan, getCreator(plan), false, false))
                .collect(Collectors.toList()));
    }

    private Creator getCreator(final Plan plan) {
        return creatorRepository.findById(plan.getCreatorId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 크리에이터입니다."));
    }

    public List<GetPlanResponse> getPlansByTheme(GetPlansByThemeRequest request) {
        return planRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Plan::getCreatedAt).reversed())
                .filter(plan -> request.getIncludes().contains(plan.getTagInfo().getTheme()))
                .filter(plan -> !request.getExcludes().contains(plan.getTagInfo().getTheme()))
                .map(plan -> new GetPlanResponse(plan, getCreator(plan),
                        scrapService.isScraped(request.getUserId(), plan.getId()), scrapService.isOrdered(request.getUserId(), plan.getId())))
                .collect(Collectors.toList());
    }
}