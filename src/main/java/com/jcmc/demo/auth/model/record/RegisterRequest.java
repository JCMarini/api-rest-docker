package com.jcmc.demo.auth.model.record;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}