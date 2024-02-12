package az.newsportal.msuser.service;

import az.newsportal.msuser.jwt.JwtAuthenticationRequest;
import az.newsportal.msuser.jwt.JwtAuthenticationResponse;
import az.newsportal.msuser.jwt.RegistrationRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse register(RegistrationRequest request);

    JwtAuthenticationResponse login(JwtAuthenticationRequest request);

}
