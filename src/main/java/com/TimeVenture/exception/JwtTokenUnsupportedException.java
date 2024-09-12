package com.TimeVenture.exception;

public class JwtTokenUnsupportedException extends RuntimeException {
    public JwtTokenUnsupportedException(String message) {
        super(message);
    }
}
