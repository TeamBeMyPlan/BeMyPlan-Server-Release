package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanDto;
import com.deploy.bemyplan.plan.application.port.in.CreatePreviewDto;
import com.deploy.bemyplan.plan.application.port.in.CreateSpotDto;
import com.deploy.bemyplan.plan.application.port.in.InquiryPlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
class InquiryPlanService implements InquiryPlanUseCase {

    private final PlanRepository planRepository;

    private final SpotRepository spotRepository;

    private final PreviewRepository previewRepository;

    private final CreatorRepository creatorRepository;

    @Override
    public List<Creator> getCreators() {
        return creatorRepository.findAll();
    }

    @Override
    public List<CreatePlanDto> getPlans() {
        return planRepository.findAll()
                .stream()
                .map(CreatePlanDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreatePlanDto getPlan(Long planId) {
        return planRepository.findById(planId)
                .map(CreatePlanDto::from)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<CreateSpotDto> getSpots(Long planId) {
        List<Spot> spots = spotRepository.findAllByPlanId(planId);
        return spots
                .stream()
                .map(spot -> {
                    int spotSeq = getSpotSeq(spots, spot.getId());
                    return CreateSpotDto.from(spotSeq, spot);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CreatePreviewDto> getPreviews(Long planId) {
        List<Spot> spots = spotRepository.findAllByPlanId(planId);

        return previewRepository.findAllPreviewByPlanId(planId)
                .stream()
                .map(preview -> {
                    int spotSeq = getSpotSeq(spots, preview.getSpotId());
                    return CreatePreviewDto.from(spotSeq, preview);
                })
                .collect(Collectors.toList());
    }

    private int getSpotSeq(List<Spot> spots, Long spotId) {
        for (int i = 0; i < spots.size(); i++) {
            if (spots.get(i).getId().equals(spotId)) {
                return i;
            }
        }

        return -1;
    }

    private int getDay(Long spotId) {
        return spotRepository.findById(spotId)
                .map(Spot::getDay)
                .orElseThrow(IllegalStateException::new);
    }
}
