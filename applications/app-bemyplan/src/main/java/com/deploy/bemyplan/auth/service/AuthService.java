package com.deploy.bemyplan.auth.service;

import com.bemyplan.auth.application.LoginDto;
import com.bemyplan.auth.application.SignUpDto;

public interface AuthService {

    Long signUp(SignUpDto request);
    Long login(LoginDto request);
}