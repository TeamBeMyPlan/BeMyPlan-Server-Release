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
public class TempUserInfoResponse {
    private Long userId;
    private String nickname;

    public static TempUserInfoResponse of(Long userId, String nickname) {
        return new TempUserInfoResponse(userId, nickname);
    }
}
