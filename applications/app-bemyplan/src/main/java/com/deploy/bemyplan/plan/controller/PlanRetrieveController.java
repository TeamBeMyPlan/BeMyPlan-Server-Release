package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.plan.service.PlanRetrieveService;
import com.deploy.bemyplan.plan.service.dto.response.PlanDetailResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanListResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanMainInfoResponse;
import com.deploy.bemyplan.plan.service.dto.response.SpotMoveInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlanRetrieveController {

    private final PlanRetrieveService planRetrieveService;

    @ApiOperation("[인증] 여행일정 목록들을 조회합니다 (여행지별 O, 정렬 기준 - 최신순(default), 구매 많은 순(orderCnt), 스크랩 많은 순(scrapCnt))")
    @GetMapping("/v1/plans")
    public PlanListResponse getPlans(@UserId final Long userId, @ModelAttribute @Valid final RetrievePlansRequest request) {
        return planRetrieveService.retrievePlans(
                userId,
                request.getRegion(),
                request.getSort()
        );
    }

    @ApiOperation("[인증] 비마이플랜이 추천하는 여행일정 목록들을 조회합니다.")
    @GetMapping("/v1/plans/bemyplanPick")
    public List<PlanMainInfoResponse> getPickList(@UserId final Long userId) {
        return planRetrieveService.getPickList(userId);
    }

    @ApiOperation("여행일정 페이지 - 특정 여행일정의 내용을 상세조회합니다.")
    @GetMapping("/v1/plans/{planId}/detail")
    public PlanDetailResponse getPlanDetailInfo(@PathVariable final Long planId) {
        return planRetrieveService.getPlanDetailInfo(planId);
    }

    @ApiOperation("여행일정 페이지 - 장소 간 이동수단/소요시간을 조회합니다. (일자별 조회)")
    @GetMapping("/v1/plans/{planId}/moveInfo")
    public List<SpotMoveInfoResponse> getSpotMoveInfos(@PathVariable final Long planId) {
        return planRetrieveService.getSpotMoveInfos(planId);
    }

    @ApiOperation("[인증] 홈 화면 여행 일정 목록을 조회합니다. (정렬 기준 - 구매 많은 순 10개, 최신 순 10개)")
    @GetMapping("/v2/main/plans")
    public List<PlanMainInfoResponse> getPlansByOrder(@UserId final Long userId, @RequestParam(defaultValue = "createdAt") final String sort){
        return planRetrieveService.getPlansByOrder(userId, sort);
    }
}