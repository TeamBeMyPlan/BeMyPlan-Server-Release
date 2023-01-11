package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.image.s3.S3Locator;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
class PlanMapper {

    Plan toDomain(PlanDto planDto, CreatorDto creator) {
        return Plan.newInstance(
                creator.getId(),
                RegionCategory.JEJU,
                planDto.getRegion(),
                S3Locator.get(planDto.getThumbnail()),
                planDto.getTitle(),
                planDto.getDescription(),
                new TagInfo(planDto.getConcept(),
                        planDto.getPartner(),
                        planDto.getVehicle()
                        , Money.wons(planDto.getCost()),
                        planDto.getPeriod()),
                planDto.getPrice(),
                PlanStatus.ACTIVE,
                RcmndStatus.of(planDto.isRecommend()),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
