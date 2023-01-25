package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.user.Creator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdminPlanController {

    private final CreatePlanService createPlanService;

    @PostMapping("/api/v1/plan")
    public void createPlan(@RequestBody CreatePlanRequest request) {
        createPlanService.createPlan(request);
    }

    @GetMapping("/api/v1/creator")
    public List<Creator> getCreator() {
        return createPlanService.getCreators();
    }
}
