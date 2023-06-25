package com.deploy.bemyplan.auth.service;

import com.bemyplan.auth.application.LoginCommand;
import com.bemyplan.auth.application.SignUpCommand;

public interface AuthService {

    String signUp(SignUpCommand request);
    Long login(LoginCommand request);
}