package com.jcmc.demo.core.filter;

import com.jcmc.demo.auth.dao.TokenRepository;
import com.jcmc.demo.auth.dao.UserRepository;
import com.jcmc.demo.auth.model.Token;
import com.jcmc.demo.auth.model.User;
import com.jcmc.demo.auth.service.JwtService;
import com.jcmc.demo.core.support.MessagesProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver resolver;

    @Autowired
    private MessagesProperties messagesProperties;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            if (request.getServletPath().contains("/api/v1/auth") ||
                    request.getServletPath().contains("/v3/api-docs") ||
                    request.getServletPath().contains("swagger-ui")) {
                filterChain.doFilter(request, response);
                return;
            }

            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new AuthorizationDeniedException(messagesProperties.msgErrorBearerNotExist);
            }


            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (userEmail == null || authentication != null) {
                throw new AuthorizationDeniedException(messagesProperties.msgErrorUserNotExist);
            }

            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            Optional<Token> tokenBase = tokenRepository.findByToken(jwt);

            if (tokenBase.isPresent()) {
                final boolean isTokenExpiredOrRevoked = tokenBase
                        .map(token -> !token.getIsExpired() && !token.getIsRevoked())
                        .orElse(false);

                // Guardar en MDC para visualizar en logs.
                MDC.put("user_name", userDetails.getUsername());
                MDC.put("id_user", String.valueOf(tokenBase.get().getUser().getIdUsuario()));
                final Optional<User> user = userRepository.findByEmail(userEmail);

                if (user.isPresent() && isTokenExpiredOrRevoked) {
                    final boolean isTokenValid = jwtService.isTokenValid(jwt, user.get());

                    if (isTokenValid) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        } catch (Exception ex) {
            resolver.resolveException(request, response, null, ex);
            return;
        }

        filterChain.doFilter(request, response);
    }
}