package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.RegionType;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanRandomResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private RegionType region;


    public PlanRandomResponse(Long planId, String thumbnailUrl, String title, RegionType region) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.region = region;
    }
    public static PlanRandomResponse of (Long planId, String thumbnailUrl, String title
            , RegionType region){
        PlanRandomResponse response = new PlanRandomResponse(
                planId,
                thumbnailUrl,
                title,
                region
        );
        return response;
    }
}
