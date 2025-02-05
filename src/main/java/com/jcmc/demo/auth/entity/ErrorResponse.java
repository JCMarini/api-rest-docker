package com.jcmc.demo.auth.entity;

import java.io.Serializable;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String timestamp
) implements Serializable {
}
