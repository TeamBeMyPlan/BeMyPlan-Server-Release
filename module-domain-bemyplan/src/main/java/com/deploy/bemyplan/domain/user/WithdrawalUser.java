package com.deploy.bemyplan.domain.user;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WithdrawalUser extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    @Embedded
    private SocialInfo socialInfo;

    private LocalDateTime userCreatedAt;

    private String reasonForWithdrawal;

    @Builder(access = AccessLevel.PACKAGE)
    private WithdrawalUser(Long userId, String name, SocialInfo socialInfo, LocalDateTime userCreatedAt, String reasonForWithdrawal) {
        this.userId = userId;
        this.name = name;
        this.socialInfo = socialInfo;
        this.userCreatedAt = userCreatedAt;
        this.reasonForWithdrawal = reasonForWithdrawal;
    }

    public static WithdrawalUser newInstance(@NotNull User signOutUser, String reasonForWithdrawal) {
        return WithdrawalUser.builder()
                .userId(signOutUser.getId())
                .name(signOutUser.getNickname())
                .socialInfo(SocialInfo.of(signOutUser.getSocialId(), signOutUser.getSocialType()))
                .userCreatedAt(signOutUser.getCreatedAt())
                .reasonForWithdrawal(reasonForWithdrawal)
                .build();
    }
}
