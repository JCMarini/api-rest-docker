package com.jcmc.demo.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record TokenResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
) implements Serializable {
}