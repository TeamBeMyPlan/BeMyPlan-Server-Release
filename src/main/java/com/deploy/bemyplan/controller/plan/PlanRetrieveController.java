package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.config.validator.AllowedSortProperties;
import com.deploy.bemyplan.controller.plan.dto.request.RetrievePlansRequest;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.service.plan.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlansScrollResponse;
import com.deploy.bemyplan.service.plan.PlanRetrieveService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.deploy.bemyplan.domain.plan.RcmndStatus.*;
import static java.lang.Boolean.*;

@Validated
@RequiredArgsConstructor
@RestController
public class PlanRetrieveController {

    private final PlanRetrieveService planRetrieveService;

    @ApiOperation("[인증] 여행일정 목록들을 스크롤 페이지네이션으로 조회합니다 (여행지별 O, 추천여부 O, 정렬 O)")
    @Auth
    @GetMapping("/v1/plans")
    public ApiResponse<PlansScrollResponse> getPlans(@UserId Long userId, @Valid RetrievePlansRequest request,
                                                            @AllowedSortProperties({"id", "createdAt", "orderCnt"}) Pageable pageable) {
        RcmndStatus rcmndStatus = request.getRcmnd() == TRUE ? RECOMMENDED : NONE;
        return ApiResponse.success(planRetrieveService.retrievePlans(
                userId,
                request.getSize(),
                request.getLastPlanId(),
                pageable,
                request.getRegion(),
                rcmndStatus));
    }

    @ApiOperation("여행일정 페이지 - 특정 여행일정의 미리보기 정보를 조회합니다")
    @GetMapping("/v1/plan/{planId}/preview")
    public ApiResponse<PlanPreviewResponse> getPreviewPlanInfo(@PathVariable Long planId) {
        return ApiResponse.success(planRetrieveService.getPreviewPlanInfo(planId));
    }

    @ApiOperation("여행일정 페이지 - 특정 여행일정의 내용을 상세조회합니다.")
    @GetMapping("/v1/plan/{planId}")
    public ApiResponse<PlanDetailResponse> getPlanDetailInfo(@PathVariable Long planId) {
        return ApiResponse.success(planRetrieveService.getPlanDetailInfo(planId));
    }
}