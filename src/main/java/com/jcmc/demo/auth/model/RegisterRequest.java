package com.jcmc.demo.auth.model;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}