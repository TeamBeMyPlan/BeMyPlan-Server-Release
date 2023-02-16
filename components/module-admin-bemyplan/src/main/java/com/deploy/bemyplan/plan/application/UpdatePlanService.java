package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.image.s3.S3Locator;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanDto;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Transactional
@RequiredArgsConstructor
@Service
class UpdatePlanService implements UpdatePlanUseCase {
    private final PlanRepository planRepository;

    @Override
    public void updatePlan(UpdatePlanDto request) {
        Plan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(IllegalArgumentException::new);

        plan.setCreatorId(request.getCreatorId());
        plan.setTitle(request.getTitle());
        plan.setDescription(request.getDescription());
        plan.setThumbnailUrl(S3Locator.get(request.getThumbnail()));
        plan.setPrice(request.getPrice());
        plan.setRcmndStatus(RcmndStatus.of(request.isRecommend()));
        plan.setTagInfo(new TagInfo(request.getConcept(),
                request.getPartner(),
                request.getVehicle(),
                Money.wons(request.getCost()),
                request.getPeriod(),
                0));
        plan.setRegion(request.getRegion());
        plan.setHashtags(Arrays.asList(request.getTags().split(",")));
        plan.setRecommendTargets(Arrays.asList(request.getRecommendTargets().split(",")));
    }
}
