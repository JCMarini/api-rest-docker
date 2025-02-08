package com.jcmc.demo.core.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessagesProperties {

    @Value("${msg.error.bad.credentials}")
    public String msgErrorBadCredentials;

    @Value("${msg.error.user.not.exist}")
    public String msgErrorUserNotExist;

    @Value("${msg.error.bearer.not.exist}")
    public String msgErrorBearerNotExist;

    @Value("${msg.error.token.expired}")
    public String msgErrorTokenExpired;

    @Value("${msg.error.authorization.denied}")
    public String msgErrorAuthorizationDenied;

    @Value("${msg.error.token.malformed}")
    public String msgErrorTokenMalformed;

    @Value("${msg.error.jwt.unsuported.algorithm}")
    public String msgErrorJwtUnsuportedAlgorithm;

    @Value("${msg.error.invalid.token}")
    public String msgErrorInvalidToken;
}
