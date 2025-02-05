package com.jcmc.demo.auth.entity;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}