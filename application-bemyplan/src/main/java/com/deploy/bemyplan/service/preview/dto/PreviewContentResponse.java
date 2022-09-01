package com.deploy.bemyplan.service.preview.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class PreviewContentResponse {
    private final List<String> images;
    private final String description;

    public static PreviewContentResponse of(final List<String> images, final String description) {
        return new PreviewContentResponse(images, description);
    }
}
