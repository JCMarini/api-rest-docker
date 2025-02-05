package com.jcmc.demo.auth.entity;

import java.io.Serializable;

public record AuthRequest(
        String email,
        String password
) implements Serializable {
}