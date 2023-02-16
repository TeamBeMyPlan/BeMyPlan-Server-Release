package com.deploy.bemyplan.plan.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreatePlanRequest {
    private CreatePlanDto plan;
    private List<CreateSpotDto> spots;
    private List<CreatePreviewDto> previews;
}
