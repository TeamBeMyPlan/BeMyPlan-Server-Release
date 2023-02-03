package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.user.Creator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdminPlanController {

    private final CreatePlanService createPlanService;
    private final InquiryPlanService inquiryPlanService;
    private final DeletePlanService deletePlanService;


    @PostMapping("/api/plans")
    public void createPlan(@RequestBody CreatePlanRequest request) {
        createPlanService.createPlan(request);
    }

    @GetMapping("/api/v1/creator")
    public List<Creator> getCreator() {
        return createPlanService.getCreators();
    }

    @GetMapping("/api/plans")
    public List<PlanDto> getPlans() {
        return inquiryPlanService.getPlans();
    }

    @GetMapping("/api/plans/{planId}")
    public PlanDto getPlan(@PathVariable Long planId) {
        return inquiryPlanService.getPlan(planId);
    }

    @DeleteMapping("/api/plans/{planId}")
    void deletePlan(@PathVariable Long planId) {
        deletePlanService.deletePlan(planId);
    }
}
