package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.config.validator.AllowedSortProperties;
import com.deploy.bemyplan.controller.plan.dto.request.RetrievePlansRequest;
import com.deploy.bemyplan.service.plan.PlanRetrieveService;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyBookmarkListRequestDto;
import com.deploy.bemyplan.service.plan.dto.request.RetrieveMyOrderListRequestDto;
import com.deploy.bemyplan.service.plan.dto.request.RetrievePickListRequestDto;
import com.deploy.bemyplan.service.plan.dto.response.OrdersScrollResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponse;
import com.deploy.bemyplan.service.plan.dto.response.PlansScrollResponse;
import com.deploy.bemyplan.service.plan.dto.response.ScrapsScrollResponse;
import com.deploy.bemyplan.service.plan.dto.response.SpotMoveInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation("[인증] 여행일정 목록들을 스크롤 페이지네이션으로 조회합니다 (여행지별 O, 정렬 O)")
    @Auth
    @GetMapping("/v1/plans")
    public ApiResponse<PlansScrollResponse> getPlans(@UserId Long userId, @Valid RetrievePlansRequest request,
                                                            @AllowedSortProperties({"id", "createdAt", "orderCnt"}) Pageable pageable) {
        return ApiResponse.success(planRetrieveService.retrievePlans(
                userId,
                request.getSize(),
                request.getAuthorId(),
                request.getLastPlanId(),
                pageable,
                request.getRegion())
        );
    }

    @ApiOperation("[인증] 비마이플랜이 추천하는 여행일정 목록들을 스크롤 페이지네이션으로 조회합니다.")
    @Auth
    @GetMapping("/v1/plans/bemyplanPick")
    public ApiResponse<PlansScrollResponse> getPickList(@UserId Long userId, @Valid RetrievePickListRequestDto request) {
        return ApiResponse.success(planRetrieveService.getPickList(request, userId));
    }

    @ApiOperation("여행일정 페이지 - 특정 여행일정의 내용을 상세조회합니다.")
    @GetMapping("/v1/plan/{planId}")
    public ApiResponse<PlanDetailResponse> getPlanDetailInfo(@PathVariable Long planId) {
        return ApiResponse.success(planRetrieveService.getPlanDetailInfo(planId));
    }

    @ApiOperation("여행일정 페이지 - 특정 여행일정의 미리보기 정보를 조회합니다")
    @GetMapping("/v1/plan/{planId}/preview")
    public ApiResponse<PlanPreviewResponse> getPreviewPlanInfo(@PathVariable Long planId) {
        return ApiResponse.success(planRetrieveService.getPreviewPlanInfo(planId));
    }

    @ApiOperation("여행일정 페이지 - 장소 간 이동수단/소요시간을 조회합니다. (일자별 조회)")
    @GetMapping("/v1/plan/{planId}/moveInfo")
    public ApiResponse<List<SpotMoveInfoResponse>> getSpotMoveInfos(@PathVariable Long planId) {
        return ApiResponse.success(planRetrieveService.getSpotMoveInfos(planId));
    }

    @ApiOperation("[인증] 내가 스크랩한 여행일정 목록들을 스크롤 페이지네이션으로 조회합니다.")
    @Auth
    @GetMapping("/v1/plan/bookmark")
    public ApiResponse<ScrapsScrollResponse> retrieveMyBookmarkList(@UserId Long userId, @Valid RetrieveMyBookmarkListRequestDto request,
                                                                    @AllowedSortProperties({"id", "createdAt", "orderCnt"}) Pageable pageable) {
        return ApiResponse.success(planRetrieveService.retrieveMyBookmarkList(request, userId, pageable));
    }

    @ApiOperation("[인증] 내가 구매한 여행일정 목록들을 스크롤 페이지네이션으로 조회합니다.")
    @Auth
    @GetMapping("/v1/plan/orders")
    public ApiResponse<OrdersScrollResponse> retrieveMyOrderList(@UserId Long userId, @Valid RetrieveMyOrderListRequestDto request,
                                                                 @AllowedSortProperties({"id", "createdAt", "orderCnt"}) Pageable pageable) {
        return ApiResponse.success(planRetrieveService.retrieveMyOrderList(request, userId, pageable));
    }
}