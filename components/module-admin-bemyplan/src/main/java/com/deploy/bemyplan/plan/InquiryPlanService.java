package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.Spot;
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
        List<Spot> spots = spotRepository.findAllByPlanId(planId);
        return spots
                .stream()
                .map(spot -> {
                    int spotSeq = getSpotSeq(spots, spot.getId());
                    return SpotDto.from(spotSeq, spot);
                })
                .collect(Collectors.toList());
    }

    public List<PreviewDto> getPreviews(Long planId) {
        List<Spot> spots = spotRepository.findAllByPlanId(planId);

        return previewRepository.findAllPreviewByPlanId(planId)
                .stream()
                .map(preview -> {
                    int spotSeq = getSpotSeq(spots, preview.getSpotId());
                    return PreviewDto.from(spotSeq, preview);
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
