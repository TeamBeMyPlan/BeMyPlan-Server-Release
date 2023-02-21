package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReadPlanDto {
    private final Long creatorId;
    private final long id;
    private final String title;
    private final String description;
    private final String thumbnail;
    private final int price;
    private final boolean recommend;
    private final TravelMobility vehicle;
    private final TravelTheme concept;
    private final int cost;
    private final int period;
    private final TravelPartner partner;
    private final Region region;
    private final String tags;
    private final String recommendTargets;

    public static ReadPlanDto from(Plan plan) {
        return new ReadPlanDto(
                plan.getCreatorId(),
                plan.getId(),
                plan.getTitle(),
                plan.getDescription(),
                plan.getThumbnailUrl(),
                plan.getPrice(),
                plan.getRcmndStatus().isRecommend(),
                plan.getTagInfo().getMobility(),
                plan.getTagInfo().getTheme(),
                plan.getTagInfo().getBudget().getAmount().intValue(),
                plan.getTagInfo().getMonth(),
                plan.getTagInfo().getPartner(),
                plan.getRegion(),
                String.join(",", plan.getHashtags()),
                String.join(",", plan.getRecommendTargets()));
    }
}
