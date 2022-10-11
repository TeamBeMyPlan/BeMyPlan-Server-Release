package com.deploy.bemyplan.service.plan.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanRecommendTargetResponse {
    private Long planId;
    private List<String> recommendTargets;

    private PlanRecommendTargetResponse(final Long planId, final List<String> recommendTargets) {
        this.planId = planId;
        this.recommendTargets = recommendTargets;
    }
    public static PlanRecommendTargetResponse of(final Long planId, final List<String> recommendTargets){
        final PlanRecommendTargetResponse response = new PlanRecommendTargetResponse(
                planId,
                recommendTargets
        );
        return response;
    }
}
