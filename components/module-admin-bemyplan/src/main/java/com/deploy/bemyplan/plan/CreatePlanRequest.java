package com.deploy.bemyplan.plan;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreatePlanRequest {
    private CreatorDto creator;
    private PlanDto plan;
    private List<SpotDto> spots;
    private List<PreviewDto> previews;

    @Builder(builderMethodName = "testBuilder")
    CreatePlanRequest(final CreatorDto creator, final PlanDto plan, final List<SpotDto> spots, final List<PreviewDto> previews) {
        this.creator = creator;
        this.plan = plan;
        this.spots = spots;
        this.previews = previews;
    }
}