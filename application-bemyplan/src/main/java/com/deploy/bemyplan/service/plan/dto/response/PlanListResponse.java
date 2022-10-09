package com.deploy.bemyplan.service.plan.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanListResponse {
    private List<PlanInfoResponse> planList;

    public PlanListResponse(final List<PlanInfoResponse> planList) {
        this.planList = planList;
    }

    public static PlanListResponse of(List<PlanInfoResponse> plans) {
        return new PlanListResponse(plans);
    }
}
