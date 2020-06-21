package com.sob.identity.exception;

import lombok.Getter;

@Getter
public class IdentityGenericException extends RuntimeException{

    private IdentityStandardError error;

    public IdentityGenericException(IdentityStandardError error) {
        super(error.getErrorMessage());
        this.error = error;
    }
}
