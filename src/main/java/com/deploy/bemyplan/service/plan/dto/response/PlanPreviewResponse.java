package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanPreviewResponse {

    private PlanPreviewInfoResponse previewInfo;

    private List<PlanPreviewContentsResponse> contents = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private PlanPreviewResponse(PlanPreviewInfoResponse previewInfo) {
        this.previewInfo = previewInfo;
    }

    public static PlanPreviewResponse of(@NotNull Plan plan, @NotNull List<PreviewContent> contents) {
        PlanPreviewResponse response = PlanPreviewResponse.builder()
                .previewInfo(PlanPreviewInfoResponse.of(plan))
                .build();
        response.contents.addAll(toPreviewContentsResponse(contents));
        return response;
    }

    private static List<PlanPreviewContentsResponse> toPreviewContentsResponse(List<PreviewContent> previewContents) {
        return previewContents.stream()
                .map(content -> PlanPreviewContentsResponse.of(content))
                .collect(Collectors.toList());
    }
}