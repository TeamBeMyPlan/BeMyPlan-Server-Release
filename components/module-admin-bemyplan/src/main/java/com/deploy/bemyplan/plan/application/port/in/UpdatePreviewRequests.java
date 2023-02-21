package com.deploy.bemyplan.plan.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UpdatePreviewRequests {
    private final long planId;
    private final List<UpdatePreviewRequest> items;

    public static UpdatePreviewRequests of(long planId, List<UpdatePreviewRequest> requests) {
        return new UpdatePreviewRequests(planId, requests);
    }
}
