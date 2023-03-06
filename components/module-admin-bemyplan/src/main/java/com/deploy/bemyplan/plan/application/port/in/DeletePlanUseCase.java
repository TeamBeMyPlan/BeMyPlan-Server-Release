package com.deploy.bemyplan.plan.application.port.in;

public interface DeletePlanUseCase {
    void deletePlan(Long planId);

    void deleteSpot(long spotId);

    void deletePreview(long previewId);
}
