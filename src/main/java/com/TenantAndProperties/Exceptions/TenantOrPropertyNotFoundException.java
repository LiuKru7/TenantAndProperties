package com.TenantAndProperties.Exceptions;

public class TenantOrPropertyNotFoundException extends RuntimeException {
    public TenantOrPropertyNotFoundException(String message) {
        super(message);
    }
}
