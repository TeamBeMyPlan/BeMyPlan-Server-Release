package com.deploy.bemyplan.service.user.dto.response;

import com.deploy.bemyplan.domain.user.User;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoResponse {

    private Long userId;
    private String nickname;

    public static UserInfoResponse of(@Nullable User user) {
        return new UserInfoResponse(user.getId(), user.getNickname());
    }
}