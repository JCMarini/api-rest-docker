package com.jcmc.demo.auth.controller;

import com.jcmc.demo.auth.model.record.AuthRequest;
import com.jcmc.demo.auth.model.record.RegisterRequest;
import com.jcmc.demo.auth.model.record.TokenResponse;
import com.jcmc.demo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request) {
        final TokenResponse response = service.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request) {
        final TokenResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return service.refreshToken(authentication);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logoutUser(@RequestBody AuthRequest request) {
        boolean response = service.logout(request);
        return ResponseEntity.ok(response);
    }


}