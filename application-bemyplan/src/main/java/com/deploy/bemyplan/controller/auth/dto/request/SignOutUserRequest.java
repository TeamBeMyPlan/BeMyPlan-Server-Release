package com.deploy.bemyplan.controller.auth.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignOutUserRequest {

    @NotBlank(message = "{signOut.reason.notBlank}")
    private String reasonForWithdrawal;
}