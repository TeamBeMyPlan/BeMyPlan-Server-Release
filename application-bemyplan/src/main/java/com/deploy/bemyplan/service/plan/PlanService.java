package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.RegionType;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.service.plan.dto.response.PlanRandomResponse;
import com.deploy.bemyplan.service.user.dto.response.CreatorInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public List<PlanRandomResponse> getPlanListByRandom(RegionType region){
        Pageable RandomTen = PageRequest.of(0, 10);
        List<Plan> plans = planRepository.findPlansByRegionAndSize(region, RandomTen);
        Collections.shuffle(plans);
        return plans.stream()
                .map(p -> PlanRandomResponse.of(p.getId(), p.getThumbnailUrl(), p.getTitle(), p.getRegion()))
                .collect(Collectors.toList());
    }

    @Transactional
    public CreatorInfoResponse getCreatorInfo(Long planId){
        Plan plan = planRepository.findById(planId).orElseThrow(() -> new NotFoundException("존재하지 않는 여행 일정입니다."));
        User user = userRepository.findUserById(plan.getUserId());
        return CreatorInfoResponse.of(plan.getUserId(), user.getNickname(), plan.getDescription());
    }
}
