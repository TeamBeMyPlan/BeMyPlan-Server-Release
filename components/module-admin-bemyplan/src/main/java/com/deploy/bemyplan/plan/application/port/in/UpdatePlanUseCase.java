package com.deploy.bemyplan.plan.application.port.in;

import java.util.List;

public interface UpdatePlanUseCase {
    void updatePlan(UpdatePlanRequest updatePlanRequest);

    void updateSpots(List<UpdateSpotRequest> requests);

    void updatePreviews(List<UpdatePreviewRequest> requests);
}
