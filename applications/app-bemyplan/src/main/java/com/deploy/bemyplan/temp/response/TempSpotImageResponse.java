package com.deploy.bemyplan.temp.response;


import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TempSpotImageResponse {

    private Long imageId;
    private String url;

    public static TempSpotImageResponse of(Long imageId, String url){
        return new TempSpotImageResponse(imageId, url);
    }

    private TempSpotImageResponse() {
    }

    private TempSpotImageResponse(final Long imageId, final String url) {
        this.imageId = imageId;
        this.url = url;
    }
}
