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
public class TempSpotMoveInfoResponse {

    private int day;
    private List<TempSpotMoveInfoDetailResponse> infos = new ArrayList<>();

    public static TempSpotMoveInfoResponse of(int day, List<TempSpotMoveInfoDetailResponse> infos){
        return new TempSpotMoveInfoResponse(day, infos);
    }

}
