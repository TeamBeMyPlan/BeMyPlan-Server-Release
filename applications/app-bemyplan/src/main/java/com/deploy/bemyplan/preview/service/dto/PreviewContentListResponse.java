package com.deploy.bemyplan.preview.service.dto;

import com.deploy.bemyplan.domain.plan.Preview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewContentListResponse {
    private final List<PreviewContentResponse> previewContents;

    public static PreviewContentListResponse of(List<Preview> previews) {
        List<PreviewContentResponse> response = toPreviewContentListResponse(previews);
        return new PreviewContentListResponse(response);
    }

    @NotNull
    private static List<PreviewContentResponse> toPreviewContentListResponse(List<Preview> previews) {
        return previews.stream()
                .map(preview -> PreviewContentResponse.of(preview.getImageUrls(), preview.getDescription()))
                .collect(Collectors.toList());
    }
}
