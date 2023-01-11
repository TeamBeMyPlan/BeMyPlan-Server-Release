package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static CreatorPlanResponse of(Plan plan) {
        return new CreatorPlanResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getThumbnailUrl(),
                plan.getRegion());
    }
}
