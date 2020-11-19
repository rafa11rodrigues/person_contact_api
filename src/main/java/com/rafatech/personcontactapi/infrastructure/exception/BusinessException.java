package com.rafatech.personcontactapi.infrastructure.exception;

public class BusinessException extends GenericException {

    private static final int DEFAULT_STATUS = 400;

    public BusinessException(Throwable cause) {
        this(DEFAULT_STATUS, cause);
    }

    public BusinessException(String message, Object... args) {
        this(DEFAULT_STATUS, message, args);
    }

    public BusinessException(String message, Throwable cause, Object... args) {
        this(DEFAULT_STATUS, message, cause, args);
    }

    public BusinessException(int status, Throwable cause) {
        super(status, cause);
    }

    public BusinessException(int status, String message, Object... args) {
        super(status, message, args);
    }

    public BusinessException(int status, String message, Throwable cause, Object... args) {
        super(status, message, cause, args);
    }
}