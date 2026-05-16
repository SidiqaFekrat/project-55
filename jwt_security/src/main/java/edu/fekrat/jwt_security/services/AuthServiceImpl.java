package edu.fekrat.jwt_security.services;


import edu.fekrat.jwt_security.dtos.JwtResponse;
import edu.fekrat.jwt_security.dtos.LoginRequest;
import edu.fekrat.jwt_security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse authenticateAndGenerateToken(LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            return new JwtResponse(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public String doCommonThing(Authentication auth) {
        return "Hello " + auth.getName() + " with roles " + auth.getAuthorities();
    }

    @Override
    public String doUserThing(Authentication auth) {
        return "Hello User " + auth.getName() + " with roles " + auth.getAuthorities();
    }

    @Override
    public String doAdminThing(Authentication auth) {
        return "Hello Admin " + auth.getName() + " with roles " + auth.getAuthorities();
    }

    @Override
    public String doManagerThing(Authentication auth) {
        return "Hello Manager " + auth.getName() + " with roles " + auth.getAuthorities();
    }
}