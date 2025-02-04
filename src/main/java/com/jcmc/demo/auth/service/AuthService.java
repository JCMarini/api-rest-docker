package com.jcmc.demo.auth.service;


import com.jcmc.demo.auth.dao.TokenRepository;
import com.jcmc.demo.auth.dao.UserRepository;
import com.jcmc.demo.auth.model.record.AuthRequest;
import com.jcmc.demo.auth.model.record.RegisterRequest;
import com.jcmc.demo.auth.model.Token;
import com.jcmc.demo.auth.model.record.TokenResponse;
import com.jcmc.demo.auth.model.User;
import com.jcmc.demo.core.util.Logger;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger LOG = Logger.getLogger(AuthService.class);

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Registra un nuevo usuario /auth/register
    public TokenResponse register(final RegisterRequest request) {
        final User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        final User savedUser = repository.save(user);
        final String jwtToken = jwtService.generateToken(savedUser);
        final String refreshToken = jwtService.generateRefreshToken(savedUser);

        saveUserToken(savedUser, jwtToken);
        LOG.info("Se realizo el registro del usuario "+ user.getEmail());

        return new TokenResponse(jwtToken, refreshToken);
    }

    // Autentica un usuario /auth/login
    public TokenResponse authenticate(final AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        final User user = repository.findByEmail(request.email())
                .orElseThrow();
        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        LOG.info("Se autentico de manera correcta el usuario " + user.getEmail());
        return new TokenResponse(accessToken, refreshToken);
    }

    // Genera un nuevo token con el token existente
    public TokenResponse refreshToken(@NotNull final String authentication) {

        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String refreshToken = authentication.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return null;
        }

        final User user = this.repository.findByEmail(userEmail).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(refreshToken, user);
        if (!isTokenValid) {
            return null;
        }

        final String accessToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        LOG.info("Se hizo un refresh de token para el usuario " + user.getEmail());
        return new TokenResponse(accessToken, refreshToken);
    }

    public boolean logout(AuthRequest request){
        final User user = repository.findByEmail(request.email())
                .orElseThrow();
        return revokeAllUserTokens(user);
    }

    private void saveUserToken(User user, String jwtToken) {
        final Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
        LOG.info("Se guardo el token del usuario " + user.getEmail());
    }

    private boolean revokeAllUserTokens(final User user) {
        final List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId_user());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setIsExpired(true);
                token.setIsRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
            LOG.info("Se revocaron los token del usuario " + user.getEmail());
            return true;
        }
        return false;
    }
}