package com.TimeVenture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(JwtTokenMalformedException.class)
    public ResponseEntity<String> handleMalformedTokenException(JwtTokenMalformedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(JwtTokenMissingException.class)
    public ResponseEntity<String> handleMissingTokenException(JwtTokenMissingException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(JwtTokenExpiredException.class)
    public ResponseEntity<String> handleExpiredTokenException(JwtTokenExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(JwtTokenUnsupportedException.class)
    public ResponseEntity<String> handleUnsupportedTokenException(JwtTokenUnsupportedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(JwtTokenSignatureException.class)
    public ResponseEntity<String> handleSignatureTokenException(JwtTokenSignatureException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(JwtTokenInvalidException.class)
    public ResponseEntity<String> handleInvalidTokenException(JwtTokenInvalidException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
