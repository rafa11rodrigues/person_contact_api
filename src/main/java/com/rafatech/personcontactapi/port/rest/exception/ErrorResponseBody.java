package com.rafatech.personcontactapi.port.rest.exception;

import com.rafatech.personcontactapi.infrastructure.exception.GenericException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ErrorResponseBody {

    private static final int UNEXPECTED_ERROR_STATUS = 500;


    private int status = UNEXPECTED_ERROR_STATUS;
    private String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    private List<String> messages = new ArrayList<>();
    private String cause;
    private LocalDateTime timestamp = LocalDateTime.now();

    protected ErrorResponseBody() {}


    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getCause() {
        return cause;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ErrorResponseBody body;

        protected Builder() {
            this.body = new ErrorResponseBody();
        }

        public Builder status(int status) {
            body.status = status;
            body.error = HttpStatus.valueOf(status).getReasonPhrase();
            return this;
        }

        public Builder status(HttpStatus status) {
            body.status = status.value();
            body.error = status.getReasonPhrase();
            return this;
        }

        public Builder messages(String... messages) {
            if (messages.length > 0) {
                body.messages.addAll(Arrays.asList(messages));
            }
            return this;
        }

        public Builder messages(List<String> messages) {
            body.messages = new ArrayList<>(messages);
            return this;
        }

        public Builder cause(String cause) {
            body.cause = cause;
            return this;
        }

        public Builder cause(Throwable cause) {
            if (Objects.nonNull(cause)) {
                body.cause = cause.toString();
            }
            return this;
        }

        public ErrorResponseBody build() {
            return body;
        }
    }

    public static ErrorResponseBody of(Exception ex, HttpStatus status) {
        var statusCode = (ex instanceof GenericException)
                ? ((GenericException) ex).getStatus()
                : Objects.isNull(status)
                    ? UNEXPECTED_ERROR_STATUS
                    : status.value();
        var message = ex.getMessage();
        var cause = ex.getCause();

        return ErrorResponseBody.builder()
                .status(statusCode)
                .messages(message)
                .cause(cause)
                .build();
    }

    public static ErrorResponseBody of(Exception ex) {
        return of(ex, null);
    }
}
