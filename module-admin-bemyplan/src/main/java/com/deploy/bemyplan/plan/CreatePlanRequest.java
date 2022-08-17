package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.plan.creator.CreatorDto;
import com.deploy.bemyplan.plan.preview.PreviewDto;
import com.deploy.bemyplan.plan.spot.SpotDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CreatePlanRequest {
    private CreatorDto creator;
    private PlanDto plan;
    private List<SpotDto> spots;
    private List<PreviewDto> previews;
}
