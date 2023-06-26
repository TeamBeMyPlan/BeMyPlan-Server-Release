package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanRandomResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private RegionCategory regionCategory;
    private Region region;

    private boolean scrapStatus;
    private CreatorInfoResponse creatorInfoResponse;
    private boolean orderStatus;


    private PlanRandomResponse(final Long planId, final String thumbnailUrl, final String title, final RegionCategory regionCategory, final Region region, final boolean scrapStatus, final CreatorInfoResponse creatorInfoResponse, final boolean orderStatus) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.regionCategory = regionCategory;
        this.region = region;
        this.scrapStatus = scrapStatus;
        this.creatorInfoResponse = creatorInfoResponse;
        this.orderStatus = orderStatus;
    }

    public static PlanRandomResponse of(final Long planId, final String thumbnailUrl, final String title
            , final RegionCategory regionCategory, final Region region,
                                        final boolean scrapStatus, @NotNull final Creator creator, final boolean orderStatus) {
        final PlanRandomResponse response = new PlanRandomResponse(
                planId,
                thumbnailUrl,
                title,
                regionCategory,
                region,
                scrapStatus, CreatorInfoResponse.of(creator), orderStatus);
        return response;
    }
}
