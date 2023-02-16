package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.plan.application.port.in.DeletePlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class DeletePlanService implements DeletePlanUseCase {
    private final PlanRepository planRepository;
    private final PreviewRepository previewRepository;

    @Override
    @Transactional
    public void deletePlan(Long planId) {
        previewRepository.deleteByPlanId(planId);
        planRepository.deleteById(planId);
    }
}
