package az.newsportal.msuser.service.impl;

import az.newsportal.msuser.dao.entity.User;
import az.newsportal.msuser.dao.repo.UserRepository;
import az.newsportal.msuser.jwt.JwtAuthenticationRequest;
import az.newsportal.msuser.jwt.JwtAuthenticationResponse;
import az.newsportal.msuser.jwt.RegistrationRequest;
import az.newsportal.msuser.service.AuthenticationService;
import az.newsportal.msuser.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    @Override
    public JwtAuthenticationResponse register(RegistrationRequest request) {
        //Register the user to repository and generate a token
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public JwtAuthenticationResponse login(JwtAuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
