package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdatePlanRequest {
    private final long planId;
    private final Long creatorId;
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
}
