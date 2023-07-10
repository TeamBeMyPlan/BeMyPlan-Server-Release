package com.deploy.bemyplan.scrap.controller;

import com.deploy.bemyplan.common.controller.ResponseDTO;
import com.deploy.bemyplan.config.auth.Auth;
import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.plan.service.dto.response.PlanScrapResponse;
import com.deploy.bemyplan.scrap.service.ScrapService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scraps")
public class ScrapController {

    private final ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정을 스크랩합니다.")
    @Auth
    @PostMapping("{planId}")
    public ResponseDTO addScrap(@PathVariable final Long planId, @UserId final Long userId) {
        scrapService.addScrap(planId, userId);
        return ResponseDTO.of("스크랩 성공");
    }

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정 스크랩을 취소합니다.")
    @Auth
    @DeleteMapping("{planId}")
    public ResponseDTO deleteScrap(@UserId final Long userId, @PathVariable final Long planId) {
        scrapService.deleteScrap(userId, planId);
        return ResponseDTO.of("스크랩 취소 성공");
    }

    @ApiOperation("[인증] 여행일정 조회/상세 페이지 - 해당 여행일정 스크랩 여부를 확인합니다. (성공 시 스크랩한 상태)")
    @Auth
    @GetMapping("{planId}")
    public ResponseDTO checkScrapStatus(@PathVariable final Long planId, @UserId final Long userId) {
        scrapService.checkScrapStatus(planId, userId);
        return ResponseDTO.of("해당 여행일정을 스크랩 한 상태입니다.");
    }


    @ApiOperation("[인증] 내가 스크랩한 여행 일정을 조회합니다. (정렬 기준 - 스크랩 많은 순, 구매 많은 순, 최신 순)")
    @Auth
    @GetMapping
    public List<PlanScrapResponse> getPlanWithScrap(@UserId final Long userId, @RequestParam(defaultValue = "createdAt") final String sort) {
        return scrapService.getPlanWithScrap(userId, sort);
    }
}
