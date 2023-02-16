package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.plan.PreviewAdapter;
import com.deploy.bemyplan.plan.application.port.in.DeletePlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class DeletePlanService implements DeletePlanUseCase {
    private final PlanRepository planRepository;

    private final PreviewAdapter previewAdapter;

    @Override
    @Transactional
    public void deletePlan(Long planId) {
        previewAdapter.deleteByPlanId(planId);
        planRepository.deleteById(planId);
    }
}
