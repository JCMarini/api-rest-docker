package com.jcmc.demo.auth.model.record;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String timestamp
) {
}
