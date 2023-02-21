package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.Preview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePreviewDto {
    private int spotSeq;
    private String description;

    CreatePreviewDto(int spotSeq, String description) {
        this.spotSeq = spotSeq;
        this.description = description;
    }

    public static CreatePreviewDto from(int spotSeq, Preview preview) {
        return new CreatePreviewDto(
                spotSeq,
                preview.getDescription());
    }
}
