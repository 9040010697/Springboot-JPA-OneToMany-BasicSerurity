package com.sob.identity.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IdentityControllerAdvice {


    @ExceptionHandler(IdentityGenericException.class)
    public ErrorResponse handleAuthGenericException(IdentityGenericException exception) {
        IdentityStandardError error = exception.getError();
        return ErrorResponse.builder()
                .logCode(error.getLogCode())
                .message(error.getErrorMessage())
                .status(error.getStatus().toString())
                .build();
    }


    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        IdentityStandardError error = IdentityStandardError.AUTH_SERVICE_ERROR;
        return ErrorResponse.builder()
                .logCode(error.getLogCode())
                .message(error.getErrorMessage() +" : " +exception.getMessage())
                .status(error.getStatus().toString())
                .build();
    }
}
