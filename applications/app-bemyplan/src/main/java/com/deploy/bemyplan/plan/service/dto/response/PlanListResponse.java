package com.deploy.bemyplan.plan.service.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlanListResponse {
    private final List<PlanInfoResponse> contents = new ArrayList<>();

    private PlanListResponse(final List<PlanInfoResponse> planList) {
        this.contents.addAll(planList);
    }

    public static PlanListResponse of(final List<PlanInfoResponse> plans) {
        return new PlanListResponse(plans);
    }
}
