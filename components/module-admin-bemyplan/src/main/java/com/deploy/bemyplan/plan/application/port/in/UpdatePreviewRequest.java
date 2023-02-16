package com.deploy.bemyplan.plan.application.port.in;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdatePreviewRequest {
    private final long id;
    private final long spotId;
    private final String description;
}
