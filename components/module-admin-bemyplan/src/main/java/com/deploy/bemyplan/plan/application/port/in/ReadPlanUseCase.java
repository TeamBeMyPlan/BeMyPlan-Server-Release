package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.user.Creator;

import java.util.List;

public interface ReadPlanUseCase {
    List<Creator> getCreators();

    List<ReadPlanDto> getPlans();

    ReadPlanDto getPlan(Long planId);

    List<ReadSpotDto> getSpots(Long planId);

    List<ReadPreviewDto> getPreviews(Long planId);
}
