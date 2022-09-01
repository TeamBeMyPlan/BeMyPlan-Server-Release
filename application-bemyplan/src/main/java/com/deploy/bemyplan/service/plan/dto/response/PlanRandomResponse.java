package com.deploy.bemyplan.service.plan.dto.response;

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
    private RegionCategory region;


    public PlanRandomResponse(Long planId, String thumbnailUrl, String title, RegionCategory region) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.region = region;
    }
    public static PlanRandomResponse of (Long planId, String thumbnailUrl, String title
            , RegionCategory region){
        PlanRandomResponse response = new PlanRandomResponse(
                planId,
                thumbnailUrl,
                title,
                region
        );
        return response;
    }
}
