package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.Getter;

@Getter
public class PlanDto {
    private String title;
    private String description;
    private int price;
    private boolean recommend;
    private TravelMobility vehicle;
    private TravelTheme concept;
    private int cost;
    private int period;
    private TravelPartner partner;
}
