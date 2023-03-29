package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.RegionCategory;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlanRegionController {

    @ApiOperation("현재 활성화중인 지역 카테고리 목록들을 조회합니다.")
    @GetMapping("/v1/regions")
    public List<RegionTypeResponse> getActiveRegionTypes() {
        return Arrays.stream(RegionCategory.values())
                .sorted(Comparator.comparingInt(RegionCategory::getDisplayOrder))
                .map(RegionTypeResponse::of)
                .collect(Collectors.toList());
    }
}
