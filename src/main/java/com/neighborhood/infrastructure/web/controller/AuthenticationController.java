package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Authentication.AuthenticationService;
import com.neighborhood.application.Authentication.request.AuthenticationRequest;
import com.neighborhood.application.Authentication.request.RegisterRequest;
import com.neighborhood.application.Authentication.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController extends BaseController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody final RegisterRequest request
  ) {

    this.logger.info("Request: POST /auth/register: {}", request.toString());
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody final AuthenticationRequest request
  ) {

    this.logger.info("Request: POST /auth/authenticate: {}", request.toString());
    return ResponseEntity.ok(service.authenticate(request));
  }


}