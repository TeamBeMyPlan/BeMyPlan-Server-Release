package com.deploy.bemyplan.controller.auth.dto.response;

import lombok.*;

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