package az.newsportal.msuser.controller;

import az.newsportal.msuser.jwt.JwtAuthenticationRequest;
import az.newsportal.msuser.jwt.JwtAuthenticationResponse;
import az.newsportal.msuser.jwt.RegistrationRequest;
import az.newsportal.msuser.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

