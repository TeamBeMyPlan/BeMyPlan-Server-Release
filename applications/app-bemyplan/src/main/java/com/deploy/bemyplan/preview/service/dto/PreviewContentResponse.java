package com.deploy.bemyplan.preview.service.dto;

import java.util.List;

public final class PreviewContentResponse {
    private final List<String> images;
    private final String description;

    private PreviewContentResponse(List<String> images, String description) {
        this.images = images;
        this.description = description;
    }

    public static PreviewContentResponse of(final List<String> images, final String description) {
        return new PreviewContentResponse(images, description);
    }

    public List<String> getImages() {
        return this.images;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "PreviewContentResponse(images=" + this.getImages() + ", description=" + this.getDescription() + ")";
    }
}
