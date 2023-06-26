package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import com.deploy.bemyplan.plan.service.PlanService;
import com.deploy.bemyplan.plan.service.dto.request.GetPlansByThemeRequest;
import com.deploy.bemyplan.plan.service.dto.response.GetPlanResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanListResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponseDto;
import com.deploy.bemyplan.plan.service.dto.response.PlanRandomResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/plans")
public class PlanController {
    private final PlanService planService;

    @ApiOperation("여행 일정을 10개씩 랜덤하게 조회합니다.")
    @GetMapping("/random")
    public List<PlanRandomResponse> getPlanListByRandom(@UserId final Long userId,
                                                        @RequestParam("planId") final Long planId,
                                                        @RequestParam("regionCategory") final RegionCategory regionCategory) {
        return planService.getPlanListByRandom(userId, planId, regionCategory);
    }

    @ApiOperation("미리보기 일정 정보를 조회합니다.")
    @GetMapping("/{planId}/preview")
    public PlanPreviewResponseDto getPlanPreview(@PathVariable final Long planId) {
        return planService.getPlanPreview(planId);
    }

    @GetMapping
    PlanListResponse getPlans(@ModelAttribute @Valid final RetrievePlansRequest request) {
        return planService.getPlans(request);
    }

    @GetMapping("/theme")
    List<GetPlanResponse> getPlansByTheme(@UserId final Long userId,
                                          @RequestParam(required = false, defaultValue = "") final TravelTheme[] includes,
                                          @RequestParam(required = false, defaultValue = "") final TravelTheme[] excludes) {
        return planService.getPlansByTheme(
                new GetPlansByThemeRequest(
                        userId,
                        Arrays.stream(includes).collect(Collectors.toList()),
                        Arrays.stream(excludes).collect(Collectors.toList())));
    }
}