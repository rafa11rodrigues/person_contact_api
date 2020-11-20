package com.rafatech.personcontactapi.infrastructure.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Constraint(validatedBy = NotInFutureValidation.class)
public @interface NotInFuture {

    String message() default "Date cannot be in future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
