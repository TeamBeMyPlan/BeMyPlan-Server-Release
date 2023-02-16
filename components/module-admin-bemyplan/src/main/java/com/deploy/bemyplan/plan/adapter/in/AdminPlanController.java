package com.deploy.bemyplan.plan.adapter.in;

import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.DeletePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.InquiryPlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.PlanDto;
import com.deploy.bemyplan.plan.application.port.in.PreviewDto;
import com.deploy.bemyplan.plan.application.port.in.SpotDto;
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

    private final CreatePlanUseCase createPlanService;
    private final InquiryPlanUseCase inquiryPlanService;
    private final DeletePlanUseCase deletePlanService;


    @PostMapping("/api/plans")
    public void createPlan(@RequestBody CreatePlanRequest request) {
        createPlanService.createPlan(request);
    }

    @GetMapping("/api/plans")
    public List<PlanDto> getPlans() {
        return inquiryPlanService.getPlans();
    }

    @GetMapping("/api/plans/{planId}")
    public PlanDto getPlan(@PathVariable Long planId) {
        return inquiryPlanService.getPlan(planId);
    }

    @GetMapping("/api/plans/{planId}/spots")
    public List<SpotDto> getSpots(@PathVariable Long planId) {
        return inquiryPlanService.getSpots(planId);
    }

    @GetMapping("/api/plans/{planId}/previews")
    public List<PreviewDto> getPreviews(@PathVariable Long planId) {
        return inquiryPlanService.getPreviews(planId);
    }

    @DeleteMapping("/api/plans/{planId}")
    void deletePlan(@PathVariable Long planId) {
        deletePlanService.deletePlan(planId);
    }

    @GetMapping("/api/creator")
    public List<Creator> getCreator() {
        return inquiryPlanService.getCreators();
    }
}
