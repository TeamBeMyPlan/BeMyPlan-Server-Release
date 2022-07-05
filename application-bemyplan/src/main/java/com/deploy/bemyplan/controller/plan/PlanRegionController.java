package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.service.mapper.plan.RegionTypeMapper;
import com.deploy.bemyplan.service.mapper.plan.dto.response.RegionTypeResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanRegionController {

    @ApiOperation("현재 활성화중인 지역 카테고리 목록들을 조회합니다.")
    @GetMapping("/v1/plan/regions")
    public ApiResponse<List<RegionTypeResponse>> getActiveRegionTypes() {
        return ApiResponse.success(RegionTypeMapper.getInstance().getActiveRegionTypes());
    }
}
