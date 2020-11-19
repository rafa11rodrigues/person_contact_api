package com.rafatech.personcontactapi.infrastructure.exception;

public class NotFoundException extends BusinessException {

    private static int STATUS = 404;

    public NotFoundException(Throwable cause) {
        super(STATUS, cause);
    }

    public NotFoundException(String message, Object... args) {
        super(STATUS, message, args);
    }

    public NotFoundException(String message, Throwable cause, Object... args) {
        super(STATUS, message, cause, args);
    }
}
