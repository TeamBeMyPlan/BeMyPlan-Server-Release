package com.deploy.bemyplan.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "profile_image", length = 255, nullable = false)
    private String profileImage;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(nullable = true)
    private Long userId;

    private Creator(final String description, final String profileImage, final String name) {
        this.description = description;
        this.profileImage = profileImage;
        this.name = name;
    }

    public static Creator newInstance(final String description, final String profileImage, final String name) {
        return new Creator(description, profileImage, name);
    }

    public void connectUserAccount(Long userId) {
        this.userId = userId;
    }
}
