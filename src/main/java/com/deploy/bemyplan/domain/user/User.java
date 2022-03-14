package com.deploy.bemyplan.domain.user;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Embedded
    private SocialInfo socialInfo;

    @Builder(access = AccessLevel.PUBLIC)
    private User(String socialId, UserSocialType socialType, String nickname, String email) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.nickname = nickname;
        this.email = email;
    }

    public static User newInstance(String socialId, UserSocialType socialType, String name, String email) {
        return User.builder()
                .socialId(socialId)
                .socialType(socialType)
                .nickname(name)
                .email(email)
                .build();
    }

    @NotNull
    public String getSocialId() {
        return this.socialInfo.getSocialId();
    }

    @NotNull
    public UserSocialType getSocialType() {
        return this.socialInfo.getSocialType();
    }
}
