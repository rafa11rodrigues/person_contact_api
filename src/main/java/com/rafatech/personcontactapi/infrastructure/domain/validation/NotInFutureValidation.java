package com.rafatech.personcontactapi.infrastructure.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Objects;

public class NotInFutureValidation implements ConstraintValidator<NotInFuture, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return Objects.isNull(value) || !value.isAfter(LocalDate.now());
    }
}
