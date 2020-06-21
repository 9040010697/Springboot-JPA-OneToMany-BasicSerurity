package com.sob.identity.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum IdentityStandardError {
    INVALID_LOGIN_CRED("Invalid login credentials", 4000, HttpStatus.BAD_REQUEST),
    AUTH_SERVICE_ERROR("exception occurred during operation", 5000, HttpStatus.INTERNAL_SERVER_ERROR);


    private String errorMessage;
    private int logCode;
    private HttpStatus status;
}
