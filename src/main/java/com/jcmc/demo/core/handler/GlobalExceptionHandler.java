package com.jcmc.demo.core.handler;

import com.jcmc.demo.auth.entity.ErrorResponse;
import com.jcmc.demo.core.util.Logger;
import com.jcmc.demo.core.util.UuidUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger L0G = Logger.getLogger(GlobalExceptionHandler.class);

    // Manejar la excepción ExpiredJwtException
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "error",
                "Token expired",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Manejar la excepción AuthorizationDeniedException
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handlerAuthorizationDeniedException(AuthorizationDeniedException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "error",
                "Authorization Denied",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Manejar la excepción MalformedJwtException
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "error",
                "Token Malformed",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejar la excepción UnsupportedJwtException
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedJwtException(UnsupportedJwtException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "error",
                "Unsupported JWT algorithm",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejar la excepción JwtException (genérica)
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "error",
                "Invalid token",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejar la excepción AccessDeniedException (para control de acceso)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "error",
                "Forbidden",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }


    // Manejar la excepcion cuando no existen credenciales para hacer login.
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handlerBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "error",
                "Bad credentials",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejar la excepcion de
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handlerDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "error",
                "No se puedo realizar esta accion",
                UuidUtil.getUUID()
        );
        L0G.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejar otras excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error",
                "Internal server error",
                UuidUtil.getUUID()
        );
        L0G.error(ex.getCause().getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}