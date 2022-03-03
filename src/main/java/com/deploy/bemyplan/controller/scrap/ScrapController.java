package com.deploy.bemyplan.controller.scrap;


import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.service.scrap.ScrapService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ScrapController {

    private final ScrapService scrapService;

    @ApiOperation("[스크랩 생성] 처음 스크랩을 눌렀을 경우 스크랩 생성, 스크랩을 취소 후 다시 스크랩 할 경우 스크랩 활성화")
    @PostMapping("/v1/scrap/{planId}")
    public ApiResponse<String> CreateOrAddScrap(@UserId Long userId, @RequestParam Long planId){
        scrapService.CreateOrAddScrap(userId, planId);
        return ApiResponse.SUCCESS;
    }

    @ApiOperation("[스크랩 취소] 스크랩이 활성화 되어 있는 상태에서 한번 더 누르면 스크랩 취소.")
    @DeleteMapping("/v1/scrap/{planId}")
    public ApiResponse<String> deleteScrap(@UserId Long userId, @RequestParam Long planId){
        scrapService.deleteScrap(userId, planId);
        return ApiResponse.SUCCESS;
    }

}
