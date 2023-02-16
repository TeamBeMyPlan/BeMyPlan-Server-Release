package com.deploy.bemyplan.plan.adapter.in;

import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UpdatePlanParam {
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
}
