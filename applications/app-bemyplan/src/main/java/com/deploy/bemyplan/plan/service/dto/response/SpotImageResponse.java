package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.SpotImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotImageResponse extends AuditingTimeResponse {

    private Long imageId;
    private String url;

    public static SpotImageResponse of(SpotImage spotImage) {
        SpotImageResponse response = new SpotImageResponse(spotImage.getId(), spotImage.getUrl());
        response.setBaseTime(spotImage.getCreatedAt(), spotImage.getUpdatedAt());
        return response;
    }
}
