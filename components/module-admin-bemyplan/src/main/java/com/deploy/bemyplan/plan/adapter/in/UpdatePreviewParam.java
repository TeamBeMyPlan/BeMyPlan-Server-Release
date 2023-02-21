package com.deploy.bemyplan.plan.adapter.in;

import com.deploy.bemyplan.plan.application.port.in.UpdatePreviewRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePreviewParam {
    private Long id;
    private long spotId;
    private String description;

    public UpdatePreviewRequest toRequest() {
        return new UpdatePreviewRequest(
                id,
                spotId,
                description
        );
    }
}
