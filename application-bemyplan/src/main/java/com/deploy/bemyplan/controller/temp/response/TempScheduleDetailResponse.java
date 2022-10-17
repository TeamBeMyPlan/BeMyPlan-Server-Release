package com.deploy.bemyplan.controller.temp.response;


import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempScheduleDetailResponse {
    private List<TempSpotDetailResponse> spots = new ArrayList<>();

    public static TempScheduleDetailResponse of (List<TempSpotDetailResponse> spots){
        return new TempScheduleDetailResponse(spots);
    }

}
