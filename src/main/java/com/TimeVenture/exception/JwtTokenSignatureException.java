package com.TimeVenture.exception;

public class JwtTokenSignatureException extends RuntimeException {
    public JwtTokenSignatureException(String message) {
        super(message);
    }
}
