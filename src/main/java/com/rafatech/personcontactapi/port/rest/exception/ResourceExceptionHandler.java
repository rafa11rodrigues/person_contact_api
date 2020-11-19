package com.rafatech.personcontactapi.port.rest.exception;

import com.rafatech.personcontactapi.infrastructure.exception.ConstraintViolationListException;
import com.rafatech.personcontactapi.infrastructure.exception.GenericException;
import com.rafatech.personcontactapi.infrastructure.exception.MessageResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationListException.class)
    public ResponseEntity<?> handleConstraintViolationListException(ConstraintViolationListException ex, WebRequest request) {
        var response = ErrorResponseBody.builder()
                .status(ex.getStatus())
                .messages(ex.getMessages())
                .build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.valueOf(ex.getStatus()), request);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleGenericException(GenericException ex, WebRequest request) {
        var response = ErrorResponseBody.of(ex);
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.valueOf(ex.getStatus()), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var response = ErrorResponseBody.builder()
                .status(status)
                .messages("Malformed request body")
                .cause(ex.getCause())
                .build();
        return handleExceptionInternal(ex, response, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var messages = ex.getBindingResult().getFieldErrors()
                .stream().map(field -> {
                    return MessageResolver.resolve(field.getDefaultMessage());
        }).collect(Collectors.toList());
        var response = ErrorResponseBody.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(messages)
                .cause(ex.getCause())
                .build();
        return handleExceptionInternal(ex, response, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var messages = ex.getBindingResult().getFieldErrors()
                .stream().map(field -> {
                    return MessageResolver.resolve(field.getDefaultMessage(), field.getArguments());
                }).collect(Collectors.toList());

        var response = ErrorResponseBody.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messages(messages)
                .cause(ex.getCause())
                .build();
        return handleExceptionInternal(ex, response, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request) {
        var response = ErrorResponseBody.of(ex);
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        if (Objects.isNull(body) || body instanceof String) {
            String message = Objects.isNull(body) ? "An unexpected error has occurred" : (String) body;
            body = ErrorResponseBody.builder()
                    .status(status)
                    .messages(message)
                    .cause(ex.getCause())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
