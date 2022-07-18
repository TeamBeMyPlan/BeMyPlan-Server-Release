package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.service.order.OrderService;
import com.deploy.bemyplan.service.scrap.ScrapService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlanController {

    private final ScrapService scrapService;
    private final OrderService orderService;

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

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정 스크랩 여부를 확인합니다. (성공 시 스크랩한 상태)")
    @Auth
    @GetMapping("/v1/plan/scrap/{planId}")
    public ApiResponse<String> checkScrapStatus(@PathVariable Long planId, @UserId Long userId){
        scrapService.checkScrapStatus(planId, userId);
        return ApiResponse.SUCCESS;
    }

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정 구매 여부를 확인합니다. (성공 시 구매하지 않은 상태)")
    @Auth
    @GetMapping("/v1/plan/order/{planId}")
    public ApiResponse<String> checkOrderStatus(@PathVariable Long planId, @UserId Long userId){
        orderService.checkOrderStatus(planId, userId);
        return ApiResponse.SUCCESS;
    }
}
