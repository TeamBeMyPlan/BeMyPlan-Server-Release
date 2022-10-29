package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanPreviewResponse extends AuditingTimeResponse {

    private PlanPreviewInfoResponse previewInfo;

    private List<PlanPreviewContentsResponse> previewContents = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private PlanPreviewResponse(PlanPreviewInfoResponse previewInfo) {
        this.previewInfo = previewInfo;
    }

    public static PlanPreviewResponse of(@NotNull Plan plan, String nickname, List<PreviewContent> contents) {
        PlanPreviewResponse response = new PlanPreviewResponse(PlanPreviewInfoResponse.of(plan, nickname));
        response.previewContents.addAll(toPreviewContentsResponse(contents));
        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }

    private static List<PlanPreviewContentsResponse> toPreviewContentsResponse(List<PreviewContent> previewContents) {
        return previewContents.stream()
                .map(content -> PlanPreviewContentsResponse.of(content))
                .collect(Collectors.toList());
    }
}