package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class InquiryPlanService {

    private final PlanRepository planRepository;

    public List<PlanDto> getPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanDto::from)
                .collect(Collectors.toList());
    }
}
