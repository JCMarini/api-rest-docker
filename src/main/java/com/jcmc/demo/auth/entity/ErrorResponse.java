package com.jcmc.demo.auth.entity;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String timestamp
) {
}
