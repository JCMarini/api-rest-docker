package com.jcmc.demo.auth.controller;

import com.jcmc.demo.auth.dao.UserRepository;
import com.jcmc.demo.auth.entity.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> usuarios = userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getName(), user.getEmail())).toList();

        return ResponseEntity.ok(usuarios);
    }
}
