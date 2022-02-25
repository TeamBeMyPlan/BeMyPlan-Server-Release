package com.deploy.bemyplan.controller.auth.dto.request;

import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.service.auth.dto.request.SignUpDto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequestDto {

    @NotBlank(message = "{auth.token.notBlank}")
    private String token;

    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,15}$", message = "{user.nickname.format}")
    private String nickname;

    @NotNull(message = "{user.email.notNull")
    @Email(message = "{user.email.format}")
    private String email;

    @NotNull(message = "{user.socialType.notNull}")
    private UserSocialType socialType;

    public SignUpDto toServiceDto() {
        return SignUpDto.of(token, nickname, email, socialType);
    }
}