package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.RegionCategory;

public class RetrievePlansRequest {
    private RegionCategory region;
    private String sort = "createdAt";

    private RetrievePlansRequest(RegionCategory region, String sort) {
        this.region = region;
        this.sort = sort;
    }

    private RetrievePlansRequest() {
    }

    public RegionCategory getRegion() {
        return this.region;
    }

    public String getSort() {
        return this.sort;
    }

    public String toString() {
        return "RetrievePlansRequest(region=" + this.getRegion() + ", sort=" + this.getSort() + ")";
    }
}