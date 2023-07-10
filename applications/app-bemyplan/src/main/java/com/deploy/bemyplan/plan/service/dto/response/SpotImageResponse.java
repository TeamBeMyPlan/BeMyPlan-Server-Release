package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.SpotImage;

public class SpotImageResponse extends AuditingTimeResponse {

    private Long imageId;
    private String url;

    private SpotImageResponse(Long imageId, String url) {
        this.imageId = imageId;
        this.url = url;
    }

    private SpotImageResponse() {
    }

    public static SpotImageResponse of(SpotImage spotImage) {
        SpotImageResponse response = new SpotImageResponse(spotImage.getId(), spotImage.getUrl());
        response.setBaseTime(spotImage.getCreatedAt(), spotImage.getUpdatedAt());
        return response;
    }

    public Long getImageId() {
        return this.imageId;
    }

    public String getUrl() {
        return this.url;
    }

    public String toString() {
        return "SpotImageResponse(imageId=" + this.getImageId() + ", url=" + this.getUrl() + ")";
    }
}
