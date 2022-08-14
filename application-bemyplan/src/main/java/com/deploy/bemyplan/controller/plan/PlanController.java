package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.service.plan.PlanService;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlanController {

    private final PlanService planService;

    @ApiOperation("미리보기 일정 정보를 조회합니다.")
    @GetMapping("/v2/plan/{planId}/preview")
    public ApiResponse<PlanPreviewResponseDto> getPlanPreview(@PathVariable Long planId) {
        return ApiResponse.success(planService.getPlanPreview(planId));
    }
}