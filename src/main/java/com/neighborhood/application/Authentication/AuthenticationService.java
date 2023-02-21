package com.neighborhood.application.Authentication;

import com.neighborhood.application.Authentication.request.AuthenticationRequest;
import com.neighborhood.application.Authentication.request.RegisterRequest;
import com.neighborhood.application.Authentication.response.AuthenticationResponse;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.domain.Player.Role;
import com.neighborhood.infrastructure.persistence.PlayerRepository;
import com.neighborhood.infrastructure.security.JwtService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final PlayerRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(final RegisterRequest request) {

    final var user = Player.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.PLAYER)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
    repository.save(user);
    final var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(final AuthenticationRequest request) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    final var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    final var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
