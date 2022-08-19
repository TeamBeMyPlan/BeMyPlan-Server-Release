package com.deploy.bemyplan.plan;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreviewDto {
    private String description;
    private String image;

    @Builder(builderMethodName = "testBuilder")
    PreviewDto(final String description, final String image) {
        this.description = description;
        this.image = image;
    }
}
