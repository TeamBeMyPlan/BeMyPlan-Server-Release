package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.controller.plan.dto.request.PlanRandomRequest;
import com.deploy.bemyplan.service.plan.PlanService;
import com.deploy.bemyplan.service.plan.dto.response.PlanRandomResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlanController {
    private final PlanService planService;

    @ApiOperation("여행 일정을 10개씩 랜덤하게 조회합니다.")
    @GetMapping("/v2/plan/random")
    public ApiResponse<List<PlanRandomResponse>> getPlanListByRandom(@Valid PlanRandomRequest request){
        return ApiResponse.success(planService.getPlanListByRandom(request.getRegion(), request.getSize()));
    }
}
