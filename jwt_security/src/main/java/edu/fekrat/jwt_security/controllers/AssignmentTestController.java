package edu.fekrat.jwt_security.controllers;
import org.springframework.web.bind.annotation.RestController;
import edu.fekrat.jwt_security.dtos.ApiExceptionDto;
import edu.fekrat.jwt_security.dtos.JwtResponse;
import edu.fekrat.jwt_security.dtos.LoginRequest;
import edu.fekrat.jwt_security.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AssignmentTestController {
    private final AuthService authService;
    @Autowired
    public AssignmentTestController(AuthService authService) {
        this.authService = authService;
    }

    // this is public and accessible to authorized and unauthorized
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint and no authentication required.";
    }

    //this authenticates users and returns a JWT token upon successful login
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse response = authService.authenticateAndGenerateToken(request);
        return ResponseEntity.ok(response);
    }

    // ROLE RESTRICTED ENDPOINTS

    // for SUER Role
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/useronly")
    public String userOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return authService.doUserThing(auth);
    }


    // for MANAGER Role
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager/manageronly")
    public String managerOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return authService.doManagerThing(auth);
    }

    //for ADMIN Role
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/adminonly")
    public String adminOnly() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return authService.doAdminThing(auth);
    }

    // any authenticated role (USER, MANAGER, ADMIN)
    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @GetMapping("/common/allok")
    public String commonAllOk() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return authService.doCommonThing(auth);
    }


    // Exception Handling

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(
            BadCredentialsException ex, HttpServletRequest request) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                request.getRequestURI(),
                ex.getClass().getSimpleName()
        );
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException( Exception ex, HttpServletRequest request) {
        ApiExceptionDto apiExceptionDto = new ApiExceptionDto(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI(),
                ex.getClass().getSimpleName()
        );
        return new ResponseEntity<>(apiExceptionDto, HttpStatus.BAD_REQUEST);
    }

}



