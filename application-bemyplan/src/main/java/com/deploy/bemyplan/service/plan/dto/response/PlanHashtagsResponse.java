package com.deploy.bemyplan.service.plan.dto.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanHashtagsResponse {
    private Long planId;
    private List<String> hashTags;

    private PlanHashtagsResponse(final Long planId, final List<String> hashTags) {
        this.planId = planId;
        this.hashTags = hashTags;
    }
    public static PlanHashtagsResponse of(final Long planId, final List<String> hashTags){
        PlanHashtagsResponse response = new PlanHashtagsResponse(
                planId,
                hashTags
        );
        return response;
    }
}
