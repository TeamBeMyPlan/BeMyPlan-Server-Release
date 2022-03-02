package com.deploy.bemyplan.controller.scrap;


import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.service.scrap.ScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
public class ScrapController {

    private final ScrapService scrapService;



    @ApiOperation("스크랩이 없으면 생성, 있으면 취소")
    @PostMapping("/v1/{planId}/scrap")
    public ApiResponse<String> CreateOrdeleteScrap(@UserId Long userId, @RequestParam Long planId){
        scrapService.CreateOrAddScrap(userId, planId);
        return ApiResponse.success("스크랩 등록 완료.");
    }

    @ApiOperation("한번 스크랩을 하고 취소한뒤 다시 스크랩 할 경우.")
    @DeleteMapping("/v1/{planId}/scrap")
    public ApiResponse<String> deleteScrap(@UserId Long userId, @RequestParam Long planId){
        scrapService.deleteScrap(userId, planId);
        return ApiResponse.success("스크랩 취소 완료.");
    }

}
