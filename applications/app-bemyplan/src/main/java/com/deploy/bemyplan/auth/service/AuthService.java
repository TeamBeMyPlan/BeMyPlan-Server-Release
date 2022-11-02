package com.deploy.bemyplan.auth.service;

import com.deploy.bemyplan.auth.service.dto.LoginDto;
import com.deploy.bemyplan.auth.service.dto.SignUpDto;

public interface AuthService {

    Long signUp(SignUpDto request);
    Long login(LoginDto request);
}