package com.deploy.bemyplan.service.user.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckAvailableNameRequestDto {

    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,15}$", message = "{user.nickname.format}")
    private String nickname;
}
