package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.Preview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewDto {
    private int spotSeq;
    private String description;
    private String image;

    PreviewDto(int spotSeq, String description, String image) {
        this.spotSeq = spotSeq;
        this.description = description;
        this.image = image;
    }

    public static PreviewDto from(int spotSeq, Preview preview) {
        return new PreviewDto(
                spotSeq,
                preview.getDescription(),
                preview.getImageUrls().get(0));
    }
}
