package de.szut.springboot_auth_service_demo.exception;

import javax.security.sasl.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    private final String message;

    public JwtAuthenticationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
