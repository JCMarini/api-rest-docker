package com.jcmc.demo.auth.entity;

public record AuthRequest(
        String email,
        String password
) {
}