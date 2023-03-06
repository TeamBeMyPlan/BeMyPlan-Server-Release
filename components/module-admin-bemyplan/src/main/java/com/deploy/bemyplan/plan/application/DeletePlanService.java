package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.plan.application.port.in.DeletePlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
class DeletePlanService implements DeletePlanUseCase {
    private final PlanRepository planRepository;
    private final PreviewRepository previewRepository;

    private final SpotRepository spotRepository;

    @Override
    public void deletePlan(Long planId) {
        previewRepository.deleteByPlanId(planId);
        planRepository.deleteById(planId);
    }

    @Override
    public void deleteSpot(long spotId) {
        previewRepository.deleteBySpotId(spotId);
        spotRepository.deleteById(spotId);
    }

    @Override
    public void deletePreview(long previewId) {
        previewRepository.deleteById(previewId);
    }
}
