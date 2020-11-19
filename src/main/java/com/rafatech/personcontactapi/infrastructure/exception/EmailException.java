package com.rafatech.personcontactapi.infrastructure.exception;

public class EmailException extends GenericException {

    public EmailException(String message, Throwable cause, Object... args) {
        super(500, message, cause, args);
    }
}
