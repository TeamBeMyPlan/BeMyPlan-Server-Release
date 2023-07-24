package com.deploy.bemyplan.plan.service.dto.request;

import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrievePlansRequest {
    private RegionCategory region;
    private String sort = "createdAt";
    private List<TravelPartner> partners = Collections.emptyList();
    private List<TravelMobility> vehicles = Collections.emptyList();
    private int[] travelMoneyRange = {0, 0};
    private int[] travelPeriodRange = {0, 0};
}