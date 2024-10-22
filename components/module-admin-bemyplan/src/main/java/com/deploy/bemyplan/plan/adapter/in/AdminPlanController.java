package com.deploy.bemyplan.plan.adapter.in;

import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.DeletePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.ReadPlanDto;
import com.deploy.bemyplan.plan.application.port.in.ReadPlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.ReadPreviewDto;
import com.deploy.bemyplan.plan.application.port.in.ReadSpotDto;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.UpdatePreviewRequests;
import com.deploy.bemyplan.plan.application.port.in.UpdateSpotRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    void updatePlan(@PathVariable long planId, @RequestBody UpdatePlanParam param) {
        updatePlanUseCase.updatePlan(new UpdatePlanRequest(planId,
                param.getCreatorId(),
                param.getTitle(),
                param.getDescription(),
                param.getThumbnail(),
                param.getPrice(),
                param.isRecommend(),
                param.getVehicle(),
                param.getConcept(),
                param.getCost(),
                param.getPeriod(),
                param.getPartner(),
                param.getRegion(),
                param.getTags(),
                param.getRecommendTargets()));
    }

    @PutMapping("/api/plans/{planId}/spots")
    void updateSpots(@PathVariable long planId, @RequestBody List<UpdateSpotParam> params) {
        updatePlanUseCase.updateSpots(UpdateSpotRequests.of(planId, params.stream()
                .map(UpdateSpotParam::toRequest)
                .collect(Collectors.toList())));
    }


    @PutMapping("/api/plans/{planId}/previews")
    void updatePreviews(@PathVariable long planId, @RequestBody List<UpdatePreviewParam> params) {
        updatePlanUseCase.updatePreviews(UpdatePreviewRequests.of(planId, params.stream()
                .map(UpdatePreviewParam::toRequest)
                .collect(Collectors.toList())));
    }

    @DeleteMapping("/api/spots/{spotId}")
    void deleteSpot(@PathVariable long spotId) {
        deletePlanUseCase.deleteSpot(spotId);
    }

    @DeleteMapping("/api/previews/{previewId}")
    void deletePreviews(@PathVariable long previewId) {
        deletePlanUseCase.deletePreview(previewId);
    }

    @GetMapping("/api/creator")
    public List<Creator> getCreator() {
        return readPlanUseCase.getCreators();
    }
}
