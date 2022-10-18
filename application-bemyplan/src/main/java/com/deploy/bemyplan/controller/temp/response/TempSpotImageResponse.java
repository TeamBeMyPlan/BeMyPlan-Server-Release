package com.deploy.bemyplan.controller.temp.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempSpotImageResponse {

    private Long imageId;
    private String url;

    public static TempSpotImageResponse of(Long imageId, String url){
        return new TempSpotImageResponse(imageId, url);
    }

}
