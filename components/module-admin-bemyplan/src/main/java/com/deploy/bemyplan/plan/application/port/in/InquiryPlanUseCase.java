package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.user.Creator;

import java.util.List;

public interface InquiryPlanUseCase {
    List<Creator> getCreators();

    List<PlanDto> getPlans();

    PlanDto getPlan(Long planId);

    List<SpotDto> getSpots(Long planId);

    List<PreviewDto> getPreviews(Long planId);
}
