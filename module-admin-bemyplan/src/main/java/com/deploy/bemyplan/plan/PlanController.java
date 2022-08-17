package com.deploy.bemyplan.plan;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {
    @PostMapping("/api/v1/plan")
    public CreatePlanRequest createPlan(@RequestBody CreatePlanRequest request) {
        return request;
    }
}
