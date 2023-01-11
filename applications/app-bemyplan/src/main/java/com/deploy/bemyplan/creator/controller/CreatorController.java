package com.deploy.bemyplan.creator.controller;

import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.creator.service.CreatorService;
import com.deploy.bemyplan.plan.service.dto.response.CreatorPlanResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/creators")
public class CreatorController {
    private final CreatorService creatorService;

    @ApiOperation("크리에이터 - 크리에이터가 작성한 일정 목록을 조회합니다.")
    @GetMapping("/plans")
    public List<CreatorPlanResponse> getCreatorPlans(@UserId Long userId) {
        return creatorService.getCreatorPlans(userId);
    }
}
