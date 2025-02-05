package com.jcmc.demo.auth.entity;

import java.io.Serializable;

public record RegisterRequest(
        String name,
        String email,
        String password
) implements Serializable {
}