package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanPreviewResponse {

    private PlanPreviewInfoResponse previewInfo;

    private List<PlanPreviewContentsResponse> contents;

    @Builder(access = AccessLevel.PRIVATE)
    private PlanPreviewResponse(PlanPreviewInfoResponse previewInfo, List<PlanPreviewContentsResponse> contents) {
        this.previewInfo = previewInfo;
        this.contents = contents;
    }

    public static PlanPreviewResponse of(@NotNull Plan plan, @NotNull List<PreviewContent> contents) {
        return PlanPreviewResponse.builder()
                .previewInfo(PlanPreviewInfoResponse.of(plan))
                .contents(
                        contents.stream()
                                .map(content -> PlanPreviewContentsResponse.of(content))
                                .collect(Collectors.toList())
                )
                .build();
    }
}