package com.jcmc.demo.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

    @Id()
    @GeneratedValue()
    @Column(name = "id_token")
    private Integer idToken;

    @Column(unique = true, name = "token")
    private String token;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "token_type")
    private TokenType tokenType = TokenType.BEARER;

    @Column(nullable = false, name = "revoked")
    private Boolean isRevoked;

    @Column(nullable = false, name = "expired")
    private Boolean isExpired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public enum TokenType {
        BEARER
    }

}