package com.deploy.bemyplan.plan.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePreviewRequest {
    private long id;
    private long spotId;
    private String description;
}
