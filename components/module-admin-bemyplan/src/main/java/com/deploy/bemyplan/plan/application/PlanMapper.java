package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.image.s3.S3Locator;
import com.deploy.bemyplan.plan.application.port.in.PlanDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class PlanMapper {

    Plan toDomain(PlanDto planDto, int totalDays) {
        return Plan.newInstance(
                planDto.getCreatorId(),
                RegionCategory.JEJU,
                planDto.getRegion(),
                S3Locator.get(planDto.getThumbnail()),
                planDto.getTitle(),
                planDto.getDescription(),
                new TagInfo(planDto.getConcept(),
                        planDto.getPartner(),
                        planDto.getVehicle()
                        , Money.wons(planDto.getCost()),
                        planDto.getPeriod(),
                        totalDays),
                planDto.getPrice(),
                PlanStatus.ACTIVE,
                RcmndStatus.of(planDto.isRecommend()),
                Arrays.asList(planDto.getTags().split(",")),
                Arrays.asList(planDto.getRecommendTargets().split(","))
        );
    }
}
