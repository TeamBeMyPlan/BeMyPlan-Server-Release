package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.RegionCategory;

public class RegionTypeResponse {

    private RegionCategory region;
    private String name;
    private String thumbnailUrl;
    private boolean isLocked;

    private RegionTypeResponse(RegionCategory region, String name, String thumbnailUrl, boolean isLocked) {
        this.region = region;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.isLocked = isLocked;
    }

    private RegionTypeResponse() {
    }

    public static RegionTypeResponse of(RegionCategory region) {
        return new RegionTypeResponse(
                region,
                region.getRegionCategoryName(),
                region.getThumbnailUrl(),
                region.isLocked()
        );
    }

    public RegionCategory getRegion() {
        return this.region;
    }

    public String getName() {
        return this.name;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public String toString() {
        return "RegionTypeResponse(region=" + this.getRegion() + ", name=" + this.getName() + ", thumbnailUrl=" + this.getThumbnailUrl() + ", isLocked=" + this.isLocked() + ")";
    }
}