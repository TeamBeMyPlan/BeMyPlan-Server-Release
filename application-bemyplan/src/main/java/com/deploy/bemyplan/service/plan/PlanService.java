package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final PreviewRepository previewRepository;

    public void getPlanPreview(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(Error::new); // 에러 처리

        // 미리보기 db
        Preview preview = previewRepository.findPreviewByPlanId(planId)
                .orElseThrow(Error::new); //에러 처리
    }
}
