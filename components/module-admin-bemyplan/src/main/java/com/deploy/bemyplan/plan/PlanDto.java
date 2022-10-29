package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanDto {
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

    @Builder(builderMethodName = "testBuilder")
    PlanDto(final String title, final String description, final String thumbnail, final int price, final boolean recommend, final TravelMobility vehicle, final TravelTheme concept, final int cost, final int period, final TravelPartner partner) {
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
    }
}
