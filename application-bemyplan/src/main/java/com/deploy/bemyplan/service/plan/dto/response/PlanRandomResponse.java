package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanRandomResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private RegionCategory regionCategory;
    private Region region;


    public PlanRandomResponse(Long planId, String thumbnailUrl, String title, RegionCategory regionCategory, Region region) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.regionCategory = regionCategory;
        this.region = region;
    }

    public static PlanRandomResponse of(Long planId, String thumbnailUrl, String title
            , RegionCategory regionCategory, Region region) {
        PlanRandomResponse response = new PlanRandomResponse(
                planId,
                thumbnailUrl,
                title,
                regionCategory,
                region
        );
        return response;
    }
}
