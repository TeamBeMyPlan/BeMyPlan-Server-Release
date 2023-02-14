package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.SpotRepository;
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

    private final PreviewRepository previewRepository;

    public List<PlanDto> getPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanDto::from)
                .collect(Collectors.toList());
    }

    public PlanDto getPlan(Long planId) {
        return planRepository.findById(planId)
                .map(PlanDto::from)
                .orElseThrow(IllegalStateException::new);
    }

    public List<SpotDto> getSpots(Long planId) {
        return spotRepository.findAllByPlanId(planId)
                .stream()
                .map(SpotDto::from)
                .collect(Collectors.toList());
    }

    public List<PreviewDto> getPreviews(Long planId) {
        return previewRepository.findAllPreviewByPlanId(planId)
                .stream()
                .map(PreviewDto::from)
                .collect(Collectors.toList());
    }
}
