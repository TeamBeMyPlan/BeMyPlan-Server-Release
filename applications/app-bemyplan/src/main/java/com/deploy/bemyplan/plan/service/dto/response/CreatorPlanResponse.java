package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;

public final class CreatorPlanResponse {
    private Long planId;
    private String title;
    private String thumbnailUrl;
    private Region region;

    private CreatorPlanResponse(final Long planId, final String title, final String thumbnailUrl, final Region region) {
        this.planId = planId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.region = region;
    }

    private CreatorPlanResponse() {
    }

    public static CreatorPlanResponse of(Plan plan) {
        return new CreatorPlanResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getThumbnailUrl(),
                plan.getRegion());
    }

    public Long getPlanId() {
        return this.planId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public Region getRegion() {
        return this.region;
    }

    public String toString() {
        return "CreatorPlanResponse(planId=" + this.getPlanId() + ", title=" + this.getTitle() + ", thumbnailUrl=" + this.getThumbnailUrl() + ", region=" + this.getRegion() + ")";
    }
}
