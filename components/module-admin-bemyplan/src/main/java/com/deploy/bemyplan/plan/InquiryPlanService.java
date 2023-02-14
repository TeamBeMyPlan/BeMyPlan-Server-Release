package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
class InquiryPlanService {

    private final PlanRepository planRepository;

    private final SpotRepository spotRepository;

    private final CreatorRepository creatorRepository;

    public List<PlanDto> getPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanDto::from)
                .collect(Collectors.toList());
    }

    public PlanDto getPlan(Long planId) {

        Plan plan = planRepository.findById(planId)
                .orElseThrow(IllegalStateException::new);

        PlanDto planDto = planRepository.findById(planId)
                .map(PlanDto::from)
                .orElseThrow(IllegalStateException::new);



        return planDto;
    }

    public List<SpotDto> getSpots(Long planId) {
        return spotRepository.findAllByPlanId(planId)
                .stream()
                .map(SpotDto::from)
                .collect(Collectors.toList());
    }
}
