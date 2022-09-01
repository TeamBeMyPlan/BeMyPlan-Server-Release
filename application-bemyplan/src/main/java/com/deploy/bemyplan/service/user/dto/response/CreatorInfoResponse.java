package com.deploy.bemyplan.service.user.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatorInfoResponse {
    private Long userId;
    private String nickname;
    private String description;

    public CreatorInfoResponse(Long userId, String nickname, String description) {
        this.userId = userId;
        this.nickname = nickname;
        this.description = description;
    }

    public static CreatorInfoResponse of(Long userId, String nickname, String description) {
        CreatorInfoResponse response = new CreatorInfoResponse(
                userId,
                nickname,
                description
        );
        return response;
    }
}
