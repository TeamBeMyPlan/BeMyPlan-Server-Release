package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.user.Creator;

import java.util.List;

public interface InquiryPlanUseCase {
    List<Creator> getCreators();

    List<CreatePlanDto> getPlans();

    CreatePlanDto getPlan(Long planId);

    List<CreateSpotDto> getSpots(Long planId);

    List<CreatePreviewDto> getPreviews(Long planId);
}
