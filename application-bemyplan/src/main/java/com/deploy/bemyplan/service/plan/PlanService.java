package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.RegionType;
import com.deploy.bemyplan.service.plan.dto.response.PlanRandomResponse;
import lombok.RequiredArgsConstructor;
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

    public List<PlanRandomResponse> getPlanListByRandom(RegionType region, int size){
        List<Plan> plans = planRepository.findListByRegionType(region, size);
        Collections.shuffle(plans);
        return plans.stream()
                .map(p -> PlanRandomResponse.of(p.getId(), p.getThumbnailUrl(), p.getTitle(), p.getRegion()))
                .collect(Collectors.toList());
    }
}
