package com.deploy.bemyplan.temp.response;


import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TempCreatorInfoResponse {
    private Long userId;
    private String nickname;

    public static TempCreatorInfoResponse of(Long userId, String nickname) {
        return new TempCreatorInfoResponse(userId, nickname);
    }

    private TempCreatorInfoResponse() {
    }

    private TempCreatorInfoResponse(final Long userId, final String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
