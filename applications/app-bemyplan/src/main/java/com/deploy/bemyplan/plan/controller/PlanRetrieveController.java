package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.config.validator.AllowedSortProperties;
import com.deploy.bemyplan.plan.service.PlanRetrieveService;
import com.deploy.bemyplan.plan.service.dto.request.RetrieveMyBookmarkListRequestDto;
import com.deploy.bemyplan.plan.service.dto.request.RetrieveMyOrderListRequestDto;
import com.deploy.bemyplan.plan.service.dto.response.OrdersScrollResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanListResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponse;
import com.deploy.bemyplan.plan.service.dto.response.ScrapsScrollResponse;
import com.deploy.bemyplan.plan.service.dto.response.SpotMoveInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlanRetrieveController {

    private final PlanRetrieveService planRetrieveService;

    @ApiOperation("[인증] 여행일정 목록들을 조회합니다 (여행지별 O, 정렬 O)")
    @Auth
    @GetMapping("/v1/plans")
    public PlanListResponse getPlans(@UserId Long userId, @ModelAttribute @Valid RetrievePlansRequest request) {
        return planRetrieveService.retrievePlans(
                userId,
                request.getRegion()
        );
    }

    @ApiOperation("[인증] 비마이플랜이 추천하는 여행일정 목록들을 조회합니다.")
    @Auth
    @GetMapping("/v1/plans/bemyplanPick")
    public PlanListResponse getPickList(@UserId Long userId) {
        return planRetrieveService.getPickList(userId);
    }

    @ApiOperation("여행일정 페이지 - 특정 여행일정의 내용을 상세조회합니다.")
    @GetMapping("/v1/plan/{planId}")
    public PlanDetailResponse getPlanDetailInfo(@PathVariable final Long planId) {
        return planRetrieveService.getPlanDetailInfo(planId);
    }

    @ApiOperation("여행일정 페이지 - 특정 여행일정의 미리보기 정보를 조회합니다")
    @GetMapping("/v1/plan/{planId}/preview")
    public PlanPreviewResponse getPreviewPlanInfo(@PathVariable final Long planId) {
        return planRetrieveService.getPreviewPlanInfo(planId);
    }

    @ApiOperation("여행일정 페이지 - 장소 간 이동수단/소요시간을 조회합니다. (일자별 조회)")
    @GetMapping("/v1/plan/{planId}/moveInfo")
    public List<SpotMoveInfoResponse> getSpotMoveInfos(@PathVariable final Long planId) {
        return planRetrieveService.getSpotMoveInfos(planId);
    }

    @ApiOperation("[인증] 내가 스크랩한 여행일정 목록들을 스크롤 페이지네이션으로 조회합니다.")
    @Auth
    @GetMapping("/v1/plan/bookmark")
    public ScrapsScrollResponse retrieveMyBookmarkList(@UserId final Long userId, @Valid final RetrieveMyBookmarkListRequestDto request,
                                                       @AllowedSortProperties({"id", "createdAt", "orderCnt"}) final Pageable pageable) {
        return planRetrieveService.retrieveMyBookmarkList(request, userId, pageable);
    }

    @ApiOperation("[인증] 내가 구매한 여행일정 목록들을 스크롤 페이지네이션으로 조회합니다.")
    @Auth
    @GetMapping("/v1/plan/orders")
    public OrdersScrollResponse retrieveMyOrderList(@UserId final Long userId, @Valid final RetrieveMyOrderListRequestDto request,
                                                    @AllowedSortProperties({"id", "createdAt", "orderCnt"}) final Pageable pageable) {
        return planRetrieveService.retrieveMyOrderList(request, userId, pageable);
    }
}