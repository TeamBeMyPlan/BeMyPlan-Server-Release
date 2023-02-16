package com.deploy.bemyplan.plan.adapter.in;

import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.DeletePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.ReadPlanDto;
import com.deploy.bemyplan.plan.application.port.in.ReadPlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.ReadPreviewDto;
import com.deploy.bemyplan.plan.application.port.in.ReadSpotDto;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanDto;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdminPlanController {

    private final CreatePlanUseCase createPlanUseCase;
    private final ReadPlanUseCase readPlanUseCase;
    private final DeletePlanUseCase deletePlanUseCase;
    private final UpdatePlanUseCase updatePlanUseCase;

    @PostMapping("/api/plans")
    public void createPlan(@RequestBody CreatePlanRequest request) {
        createPlanUseCase.createPlan(request);
    }

    @GetMapping("/api/plans")
    public List<ReadPlanDto> getPlans() {
        return readPlanUseCase.getPlans();
    }

    @GetMapping("/api/plans/{planId}")
    public ReadPlanDto getPlan(@PathVariable Long planId) {
        return readPlanUseCase.getPlan(planId);
    }

    @GetMapping("/api/plans/{planId}/spots")
    public List<ReadSpotDto> getSpots(@PathVariable Long planId) {
        return readPlanUseCase.getSpots(planId);
    }

    @GetMapping("/api/plans/{planId}/previews")
    public List<ReadPreviewDto> getPreviews(@PathVariable Long planId) {
        return readPlanUseCase.getPreviews(planId);
    }

    @DeleteMapping("/api/plans/{planId}")
    void deletePlan(@PathVariable Long planId) {
        deletePlanUseCase.deletePlan(planId);
    }

    @PutMapping("/api/plans/{planId}")
    void updatePlan(@PathVariable long planId, @RequestBody UpdatePlanRequest request) {
        updatePlanUseCase.updatePlan(new UpdatePlanDto(planId,
                request.getCreatorId(),
                request.getTitle(),
                request.getDescription(),
                request.getThumbnail(),
                request.getPrice(),
                request.isRecommend(),
                request.getVehicle(),
                request.getConcept(),
                request.getCost(),
                request.getPeriod(),
                request.getPartner(),
                request.getRegion(),
                request.getTags(),
                request.getRecommendTargets()));
    }

    @GetMapping("/api/creator")
    public List<Creator> getCreator() {
        return readPlanUseCase.getCreators();
    }
}
