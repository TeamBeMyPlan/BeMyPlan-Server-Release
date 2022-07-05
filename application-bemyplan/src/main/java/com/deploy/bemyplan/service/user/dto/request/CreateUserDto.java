package com.deploy.bemyplan.service.user.dto.request;

import com.deploy.bemyplan.domain.user.UserSocialType;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserDto {

    @NotBlank(message = "{user.socialId.notBlank}")
    private String socialId;

    @NotNull(message = "{user.socialType.notNull}")
    private UserSocialType socialType;

    @NotBlank(message = "{user.nickname.notBlank}")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,15}$", message = "{user.nickname.format}")
    private String nickname;

    @NotNull(message = "{user.email.notNull")
    @Email(message = "{user.email.format}")
    private String email;

    public static CreateUserDto of(String socialId, UserSocialType socialType, String name, String email) {
        return new CreateUserDto(socialId, socialType, name, email);
    }
}
