package com.deploy.bemyplan.creator.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.service.dto.response.CreatorPlanResponse;
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

    public List<CreatorPlanResponse> getCreatorPlans(Long userId) {
        final Creator creator = creatorRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("크리에이터 정보가 존재하지 않습니다."));

        return planRepository.findAllByCreatorId(creator.getId())
                .stream().map(CreatorPlanResponse::of).collect(Collectors.toList());
    }
}
