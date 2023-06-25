package com.deploy.bemyplan.auth.service;

import com.bemyplan.auth.application.port.in.LoginCommand;
import com.bemyplan.auth.application.port.in.SignUpCommand;

public interface AuthService {

    String signUp(SignUpCommand request);
    Long login(LoginCommand request);
}