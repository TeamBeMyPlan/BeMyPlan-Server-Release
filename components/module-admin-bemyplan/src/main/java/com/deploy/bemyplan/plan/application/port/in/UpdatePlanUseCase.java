package com.deploy.bemyplan.plan.application.port.in;

public interface UpdatePlanUseCase {
    void updatePlan(UpdatePlanRequest updatePlanRequest);

    void updateSpots(UpdateSpotRequests requests);

    void updatePreviews(UpdatePreviewRequests requests);
}
