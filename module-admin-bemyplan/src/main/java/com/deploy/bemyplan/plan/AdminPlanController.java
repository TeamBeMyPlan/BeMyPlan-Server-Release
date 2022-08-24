package com.deploy.bemyplan.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminPlanController {

    private final CreatePlanService createPlanService;

    @PostMapping("/api/v1/plan")
    public void createPlan(@RequestBody CreatePlanRequest request) {
        createPlanService.createPlan(request);
    }
}
