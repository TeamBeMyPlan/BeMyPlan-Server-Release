package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePlanDto {

    private Long creatorId;
    private String title;
    private String description;
    private String thumbnail;
    private int price;
    private boolean recommend;
    private TravelMobility vehicle;
    private TravelTheme concept;
    private int cost;
    private int period;
    private TravelPartner partner;
    private Region region;
    private String tags;
    private String recommendTargets;

    public CreatePlanDto(final Long creatorId, final String title, final String description, final String thumbnail, final int price, final boolean recommend, final TravelMobility vehicle, final TravelTheme concept, final int cost, final int period, final TravelPartner partner, final Region region, final String tags, final String recommendTargets) {
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.recommend = recommend;
        this.vehicle = vehicle;
        this.concept = concept;
        this.cost = cost;
        this.period = period;
        this.partner = partner;
        this.region = region;
        this.tags = tags;
        this.recommendTargets = recommendTargets;
    }

    public static CreatePlanDto from(Plan plan) {
        return new CreatePlanDto(
                plan.getCreatorId(),
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
