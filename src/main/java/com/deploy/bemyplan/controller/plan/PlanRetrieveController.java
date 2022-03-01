package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.config.validator.AllowedSortProperties;
import com.deploy.bemyplan.controller.plan.dto.request.RetrievePlansRequest;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.service.plan.dto.response.PlansScrollResponse;
import com.deploy.bemyplan.service.plan.PlanRetrieveService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
}