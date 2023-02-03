package com.deploy.bemyplan.creator.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.service.dto.response.CreatorPlanResponse;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CreatorService {
    private final CreatorRepository creatorRepository;
    private final PlanRepository planRepository;

    public CreatorInfoResponse getCreatorInfo(final Long planId) {
        final Plan plan = planRepository.findById(planId).orElseThrow(() -> new NotFoundException("존재하지 않는 여행 일정입니다."));
        final Creator creator = creatorRepository.findById(plan.getCreatorId())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 크리에이터입니다."));

        return CreatorInfoResponse.of(plan.getCreatorId(), creator.getName(), creator.getDescription());
    }

    public List<CreatorPlanResponse> getCreatorPlans(Long userId) {
        final Creator creator = creatorRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("크리에이터 정보가 존재하지 않습니다."));

        return planRepository.findAllByCreatorId(creator.getId())
                .stream().map(CreatorPlanResponse::of).collect(Collectors.toList());
    }
}
