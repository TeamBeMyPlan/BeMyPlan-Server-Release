package com.deploy.bemyplan.plan.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreatePlanRequest {
    private PlanDto plan;
    private List<SpotDto> spots;
    private List<PreviewDto> previews;

    public CreatePlanRequest(final PlanDto plan, final List<SpotDto> spots, final List<PreviewDto> previews) {
        this.plan = plan;
        this.spots = spots;
        this.previews = previews;
    }
}
