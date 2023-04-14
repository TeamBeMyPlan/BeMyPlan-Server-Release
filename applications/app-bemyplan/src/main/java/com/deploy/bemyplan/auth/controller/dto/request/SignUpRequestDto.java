package com.deploy.bemyplan.auth.controller.dto.request;

import com.deploy.bemyplan.auth.service.dto.SignUpDto;
import com.deploy.bemyplan.domain.user.UserSocialType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9._]{1,15}$", message = "{user.nickname.format}")
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