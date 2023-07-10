package com.deploy.bemyplan.preview.service.dto;

import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.SpotImage;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class PreviewContentListResponse {
    private final List<PreviewContentResponse> previewContents;

    private PreviewContentListResponse(List<PreviewContentResponse> previewContents) {
        this.previewContents = previewContents;
    }

    public static PreviewContentListResponse of(List<Preview> previews) {
        List<PreviewContentResponse> response = toPreviewContentListResponse(previews);
        return new PreviewContentListResponse(response);
    }

    @NotNull
    private static List<PreviewContentResponse> toPreviewContentListResponse(List<Preview> previews) {
        return previews.stream()
                .map(preview -> PreviewContentResponse.of(
                        preview.getSpot().getImages().stream()
                                .map(SpotImage::getUrl)
                                .limit(3)
                                .collect(Collectors.toList()),
                        preview.getDescription()))
                .collect(Collectors.toList());
    }

    public List<PreviewContentResponse> getPreviewContents() {
        return this.previewContents;
    }

    public String toString() {
        return "PreviewContentListResponse(previewContents=" + this.getPreviewContents() + ")";
    }
}
