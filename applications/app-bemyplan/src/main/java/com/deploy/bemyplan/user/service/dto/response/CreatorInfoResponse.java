package com.deploy.bemyplan.user.service.dto.response;

import com.deploy.bemyplan.domain.user.Creator;
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

    private CreatorInfoResponse(Long userId, String nickname, String description) {
        this.userId = userId;
        this.nickname = nickname;
        this.description = description;
    }

    public static CreatorInfoResponse of(Creator creator) {
        return new CreatorInfoResponse(creator.getId(), creator.getName(), creator.getDescription());
    }
}
