package com.deploy.bemyplan.plan.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UpdateSpotRequests {
    private final long planId;
    private final List<UpdateSpotRequest> items;

    public static UpdateSpotRequests of(long planId, List<UpdateSpotRequest> requests) {
        return new UpdateSpotRequests(planId, requests);
    }
}
