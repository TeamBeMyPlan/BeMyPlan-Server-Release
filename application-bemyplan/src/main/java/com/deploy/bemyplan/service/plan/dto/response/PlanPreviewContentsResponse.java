package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.JsonValueType;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanPreviewContentsResponse {

    private JsonValueType type;
    private String value;

    public static PlanPreviewContentsResponse of(@NotNull PreviewContent content) {
        return new PlanPreviewContentsResponse(content.getValueType(), content.getValue());
    }
}