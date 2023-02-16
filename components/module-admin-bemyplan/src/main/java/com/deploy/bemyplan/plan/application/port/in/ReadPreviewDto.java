package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.Preview;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReadPreviewDto {
    private final long id;
    private final long spotId;
    private final String description;
    private final String image;

    public static ReadPreviewDto from(Preview preview) {
        return new ReadPreviewDto(
                preview.getId(),
                preview.getSpot().getId(),
                preview.getDescription(),
                preview.getImageUrls().get(0));
    }
}
