package com.deploy.bemyplan.service.auth;

import com.deploy.bemyplan.service.auth.dto.request.LoginDto;
import com.deploy.bemyplan.service.auth.dto.request.SignUpDto;

public interface AuthService {

    Long signUp(SignUpDto request);
    Long login(LoginDto request);
}