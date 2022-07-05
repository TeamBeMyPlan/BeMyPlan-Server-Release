package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.common.dto.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.SpotImage;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotImageResponse extends AuditingTimeResponse {

    private Long imageId;
    private String url;

    public static SpotImageResponse of(SpotImage spotImage) {
        SpotImageResponse response = new SpotImageResponse(spotImage.getId(), spotImage.getUrl());
        response.setBaseTime(spotImage);
        return response;
    }
}
