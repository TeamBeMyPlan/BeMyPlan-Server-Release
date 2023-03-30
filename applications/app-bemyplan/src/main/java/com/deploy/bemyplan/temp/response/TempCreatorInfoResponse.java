package com.deploy.bemyplan.temp.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempCreatorInfoResponse {
    private Long userId;
    private String nickname;

    public static TempCreatorInfoResponse of(Long userId, String nickname) {
        return new TempCreatorInfoResponse(userId, nickname);
    }
}
