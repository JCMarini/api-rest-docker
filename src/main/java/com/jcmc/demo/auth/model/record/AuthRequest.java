package com.jcmc.demo.auth.model.record;

public record AuthRequest(
        String email,
        String password
) {
}