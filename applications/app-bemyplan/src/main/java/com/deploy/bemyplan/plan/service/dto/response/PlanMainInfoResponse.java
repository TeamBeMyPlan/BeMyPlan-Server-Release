package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanMainInfoResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private Region region;
    private boolean scrapStatus;
    private CreatorInfoResponse creatorInfoResponse;
    private boolean orderStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private PlanMainInfoResponse(final Long planId, final String thumbnailUrl, final String title, final Region region, final boolean scrapStatus, final CreatorInfoResponse creatorInfoResponse, final boolean orderStatus, final LocalDateTime createdAt) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.region = region;
        this.scrapStatus = scrapStatus;
        this.creatorInfoResponse = creatorInfoResponse;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public static PlanMainInfoResponse of(final Long planId, final String thumbnailUrl, final String title, final Region region, final boolean scrapStatus, @NotNull Creator creator, final boolean orderStatus, final LocalDateTime createdAt) {
        return new PlanMainInfoResponse(planId, thumbnailUrl, title, region, scrapStatus, CreatorInfoResponse.of(creator), orderStatus, createdAt);
    }
}
