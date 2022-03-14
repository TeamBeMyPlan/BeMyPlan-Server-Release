package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.service.scrap.ScrapService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlanController {

    private final ScrapService scrapService;

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정을 스크랩합니다.")
    @Auth
    @PostMapping("/v1/plan/scrap/{planId}")
    public ApiResponse<String> addScrap(@PathVariable Long planId, @UserId Long userId){
        scrapService.addScrap(planId, userId);
        return ApiResponse.SUCCESS;
    }

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정 스크랩을 취소합니다.")
    @Auth
    @DeleteMapping("/v1/plan/scrap/{planId}")
    public ApiResponse<String> deleteScrap(@UserId Long userId, @PathVariable Long planId){
        scrapService.deleteScrap(userId, planId);
        return ApiResponse.SUCCESS;
    }
}
