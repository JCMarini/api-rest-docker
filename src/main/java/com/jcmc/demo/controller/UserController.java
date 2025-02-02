package com.jcmc.demo.controller;

import com.jcmc.demo.core.Logger;
import com.jcmc.demo.model.AuthRequest;
import com.jcmc.demo.model.User;
import com.jcmc.demo.service.JwtService;
import com.jcmc.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;


    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody @Validated AuthRequest authRequest) {
        try {
            // validamos que el usuario y contraseña vengan en el request
            if (authRequest.getUsername() == null || authRequest.getPassword() == null) {
                return ResponseEntity.status(400).body("Bad Request, el usuario y contaseña son necesarios");
            }
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            String jwt = jwtService.generateToken(authentication.getName());

            return ResponseEntity.ok(jwt);

        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Bad Credentials, el usuario o la contraseña son incorrectos o no existen");
        }
    }
}