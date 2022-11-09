package com.deploy.bemyplan.plan.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlanRegionController {

    @ApiOperation("현재 활성화중인 지역 카테고리 목록들을 조회합니다.")
    @GetMapping("/v1/plan/regions")
    public List<RegionTypeResponse> getActiveRegionTypes() {
        return RegionTypeMapper.getInstance().getActiveRegionTypes();
    }
}
