package com.rafatech.personcontactapi.infrastructure.exception;

public abstract class GenericException extends RuntimeException {

    protected int status;
    protected Object[] args;


    public GenericException(int status) {
        this(status, null);
    }

    public GenericException(int status, Throwable cause) {
        this(status, null, cause);
    }

    public GenericException(int status, String message, Object... args) {
        this(status, message, null, args);
    }

    public GenericException(int status, String message, Throwable cause, Object... args) {
        super(message, cause);
        this.status = status;
        this.args = args;
    }

    public int getStatus() {
        return status;
    }

    public Object[] getArgs() {
        return args;
    }


    @Override
    public String getMessage() {
        return MessageResolver.resolve(super.getMessage(), args);
    }
}