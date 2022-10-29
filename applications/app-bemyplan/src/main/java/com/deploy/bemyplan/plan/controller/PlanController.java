package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.plan.service.PlanService;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponseDto;
import com.deploy.bemyplan.plan.service.dto.response.PlanRandomResponse;
import com.deploy.bemyplan.service.user.dto.response.CreatorInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/plans")
public class PlanController {
    private final PlanService planService;
    @ApiOperation("여행 일정을 10개씩 랜덤하게 조회합니다.")
    @GetMapping("/random")
    public List<PlanRandomResponse> getPlanListByRandom(@NotNull @RequestParam("regionCategory") final RegionCategory regionCategory){
        return planService.getPlanListByRandom(regionCategory);
    }

    @ApiOperation("크리에이터 조회 - 크리에이터의 정보를 조회합니다.")
    @GetMapping("/{planId}/creator")
    public CreatorInfoResponse getSpotMoveInfos(@PathVariable final Long planId) {
        return planService.getCreatorInfo(planId);
    }
    
    @ApiOperation("미리보기 일정 정보를 조회합니다.")
    @GetMapping("/{planId}/preview")
    public PlanPreviewResponseDto getPlanPreview(@PathVariable final Long planId) {
        return planService.getPlanPreview(planId);
    }
}