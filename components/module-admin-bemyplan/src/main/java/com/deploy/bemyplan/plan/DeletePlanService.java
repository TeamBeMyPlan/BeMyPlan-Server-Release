package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
class DeletePlanService {
    private final PlanRepository planRepository;

    private final PreviewAdapter previewAdapter;

    @Transactional
    public void deletePlan(Long planId) {
        previewAdapter.deleteByPlanId(planId);
        planRepository.deleteById(planId);
    }
}
