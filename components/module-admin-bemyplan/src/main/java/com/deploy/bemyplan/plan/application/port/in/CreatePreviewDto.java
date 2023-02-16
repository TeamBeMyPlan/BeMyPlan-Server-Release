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
    private String image;

    CreatePreviewDto(int spotSeq, String description, String image) {
        this.spotSeq = spotSeq;
        this.description = description;
        this.image = image;
    }

    public static CreatePreviewDto from(int spotSeq, Preview preview) {
        return new CreatePreviewDto(
                spotSeq,
                preview.getDescription(),
                preview.getImageUrls().get(0));
    }
}
