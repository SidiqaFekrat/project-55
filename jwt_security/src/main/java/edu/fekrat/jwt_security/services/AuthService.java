package edu.fekrat.jwt_security.services;

import edu.fekrat.jwt_security.dtos.JwtResponse;
import edu.fekrat.jwt_security.dtos.LoginRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {

    JwtResponse authenticateAndGenerateToken(LoginRequest request);
    String doCommonThing(Authentication auth);
    String doUserThing(Authentication auth);
    String doAdminThing(Authentication auth);
    String doManagerThing(Authentication auth);
}
