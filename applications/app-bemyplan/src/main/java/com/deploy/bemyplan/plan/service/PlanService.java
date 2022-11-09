package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponseDto;
import com.deploy.bemyplan.plan.service.dto.response.PlanRandomResponse;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlanService {
    private final PreviewRepository previewRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public List<PlanRandomResponse> getPlanListByRandom(RegionCategory region) {
        Pageable RandomTen = PageRequest.of(0, 10);
        List<Plan> plans = planRepository.findPlansByRegionAndSize(region, RandomTen);
        Collections.shuffle(plans);
        return plans.stream()
                .map(p -> PlanRandomResponse.of(p.getId(), p.getThumbnailUrl(), p.getTitle(), p.getRegionCategory(), p.getRegion()))
                .collect(Collectors.toList());
    }

    public CreatorInfoResponse getCreatorInfo(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new NotFoundException("존재하지 않는 여행 일정입니다."));
        User user = userRepository.findUserById(plan.getUserId());
        return CreatorInfoResponse.of(plan.getUserId(), user.getNickname(), plan.getDescription());
    }

    public PlanPreviewResponseDto getPlanPreview(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 일정 (%s) 입니다", planId)));
        List<Preview> previews = previewRepository.findAllPreviewByPlanId(planId);

        final List<String> previewImages = getPreviewImages(previews);

        return PlanPreviewResponseDto.of(plan, previewImages);
    }

    @NotNull
    private List<String> getPreviewImages(List<Preview> previews) {
        return previews.stream()
                .map(preview -> preview.getImageUrls().get(0))
                .collect(Collectors.toList());
    }
}