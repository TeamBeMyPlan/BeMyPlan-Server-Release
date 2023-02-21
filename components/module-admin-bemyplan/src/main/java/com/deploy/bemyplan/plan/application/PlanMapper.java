package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.image.s3.S3Locator;
import com.deploy.bemyplan.plan.application.port.in.CreatePlanDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class PlanMapper {

    Plan toDomain(CreatePlanDto createPlanDto, int totalDays) {
        return Plan.newInstance(
                createPlanDto.getCreatorId(),
                RegionCategory.JEJU,
                createPlanDto.getRegion(),
                S3Locator.get(createPlanDto.getThumbnail()),
                createPlanDto.getTitle(),
                createPlanDto.getDescription(),
                new TagInfo(createPlanDto.getConcept(),
                        createPlanDto.getPartner(),
                        createPlanDto.getVehicle()
                        , Money.wons(createPlanDto.getCost()),
                        createPlanDto.getPeriod(),
                        totalDays),
                createPlanDto.getPrice(),
                PlanStatus.ACTIVE,
                RcmndStatus.of(createPlanDto.isRecommend()),
                Arrays.asList(createPlanDto.getTags().split(",")),
                Arrays.asList(createPlanDto.getRecommendTargets().split(","))
        );
    }
}
