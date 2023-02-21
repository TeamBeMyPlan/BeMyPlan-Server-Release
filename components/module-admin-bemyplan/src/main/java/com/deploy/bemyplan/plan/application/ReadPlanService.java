package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.application.port.in.ReadPlanDto;
import com.deploy.bemyplan.plan.application.port.in.ReadPlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.ReadPreviewDto;
import com.deploy.bemyplan.plan.application.port.in.ReadSpotDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
class ReadPlanService implements ReadPlanUseCase {

    private final PlanRepository planRepository;

    private final SpotRepository spotRepository;

    private final PreviewRepository previewRepository;

    private final CreatorRepository creatorRepository;

    @Override
    public List<Creator> getCreators() {
        return creatorRepository.findAll();
    }

    @Override
    public List<ReadPlanDto> getPlans() {
        return planRepository.findAll()
                .stream()
                .map(ReadPlanDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public ReadPlanDto getPlan(Long planId) {
        return planRepository.findById(planId)
                .map(ReadPlanDto::from)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<ReadSpotDto> getSpots(Long planId) {
        List<Spot> spots = spotRepository.findAllByPlanId(planId);
        return spots
                .stream()
                .map(ReadSpotDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadPreviewDto> getPreviews(Long planId) {
        List<Spot> spots = spotRepository.findAllByPlanId(planId);

        return previewRepository.findAllPreviewByPlanId(planId)
                .stream()
                .map(ReadPreviewDto::from)
                .collect(Collectors.toList());
    }
}
