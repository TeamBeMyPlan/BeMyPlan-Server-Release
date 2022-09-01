package com.deploy.bemyplan.plan;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewDto {
    private int spotId;
    private String description;
    private String image;

    @Builder(builderMethodName = "testBuilder")
    PreviewDto(int spotId, String description, String image) {
        this.spotId = spotId;
        this.description = description;
        this.image = image;
    }
}
