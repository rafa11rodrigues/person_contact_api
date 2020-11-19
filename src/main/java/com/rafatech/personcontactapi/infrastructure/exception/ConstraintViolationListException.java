package com.rafatech.personcontactapi.infrastructure.exception;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintViolationListException extends BusinessException {

    private List<String> messages;

    public <T> ConstraintViolationListException(Set<ConstraintViolation<T>> errors) {
        super(null);
        this.messages = errors.stream().map(error -> {
            var message = error.getMessage();
            var value = error.getInvalidValue();
            return MessageResolver.resolve(message, value);
        }).collect(Collectors.toList());
    }

    public List<String> getMessages() {
        return List.copyOf(messages);
    }

    @Override
    public String getMessage() {
        return String.join("|", messages);
    }
}
