package com.jcmc.demo.auth.model;

public record AuthRequest(
        String email,
        String password
) {
}