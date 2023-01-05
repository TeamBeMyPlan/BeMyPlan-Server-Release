package com.deploy.bemyplan.domain.user;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    @Column(nullable = false)
    private boolean active;

    @Embedded
    private SocialInfo socialInfo;

    @Column(nullable = true)
    private Long creatorId;

    private User(String socialId, UserSocialType socialType, String nickname, String email, boolean active) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.nickname = nickname;
        this.email = email;
        this.active = active;
    }

    public static User newInstance(String socialId, UserSocialType socialType, String name, String email) {
        return new User(socialId, socialType, name, email, true);
    }

    @NotNull
    public String getSocialId() {
        return this.socialInfo.getSocialId();
    }

    @NotNull
    public UserSocialType getSocialType() {
        return this.socialInfo.getSocialType();
    }

    public void inactive() {
        active = false;
    }

    public void connectCreatorAccount(Long creatorId) {
        this.creatorId = creatorId;
    }
}
