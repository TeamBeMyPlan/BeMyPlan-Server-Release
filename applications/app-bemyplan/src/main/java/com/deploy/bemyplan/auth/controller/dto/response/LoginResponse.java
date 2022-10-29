package com.deploy.bemyplan.auth.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private String sessionId;

    private Long userId;

    private String nickname;

    public static LoginResponse of(String sessionId, Long userId, String nickname) {
        return new LoginResponse(sessionId, userId, nickname);
    }
}