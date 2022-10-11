package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.service.plan.PlanService;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponseDto;
import com.deploy.bemyplan.service.plan.dto.response.PlanRandomResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanRecommendTargetResponse;
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
    public ApiResponse<List<PlanRandomResponse>> getPlanListByRandom(@NotNull @RequestParam("regionCategory") RegionCategory regionCategory){
        return ApiResponse.success(planService.getPlanListByRandom(regionCategory));
    }

    @ApiOperation("크리에이터 조회 - 크리에이터의 정보를 조회합니다.")
    @GetMapping("/{planId}/creator")
    public ApiResponse<CreatorInfoResponse> getSpotMoveInfos(@PathVariable Long planId) {
        return ApiResponse.success(planService.getCreatorInfo(planId));
    }
    
    @ApiOperation("미리보기 일정 정보를 조회합니다.")
    @GetMapping("/{planId}/preview")
    public ApiResponse<PlanPreviewResponseDto> getPlanPreview(@PathVariable Long planId) {
        return ApiResponse.success(planService.getPlanPreview(planId));
    }
    @ApiOperation("일정별 이런 분들에게 추천해요! 조회")
    @GetMapping("/recommend/{planId}")
    public PlanRecommendTargetResponse getPlanRecommendTarget(@PathVariable Long planId) {
        return planService.getPlanRecommendTarget(planId);
    }
}