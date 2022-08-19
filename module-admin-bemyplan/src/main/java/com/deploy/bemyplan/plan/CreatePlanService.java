package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePlanService {
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    public void createPlan(final CreatePlanRequest request) {
        final PlanDto planDto = request.getPlan();
        final Plan plan = planMapper.toDomain(planDto);

        planRepository.save(plan);
    }
}
