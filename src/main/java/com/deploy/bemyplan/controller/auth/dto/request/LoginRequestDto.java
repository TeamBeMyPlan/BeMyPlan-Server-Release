package com.deploy.bemyplan.controller.auth.dto.request;

import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.service.auth.dto.request.LoginDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDto {

    @NotBlank(message = "{auth.token.notBlank}")
    private String token;

    @NotNull(message = "{user.socialType.notNull}")
    private UserSocialType socialType;

    public LoginDto toServiceDto() {
        return LoginDto.of(token, socialType);
    }
}
