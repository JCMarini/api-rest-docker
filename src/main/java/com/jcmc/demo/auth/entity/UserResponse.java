package com.jcmc.demo.auth.entity;


import java.io.Serializable;

public record UserResponse(
        String name,
        String email
) implements Serializable{
}
