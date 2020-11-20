package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.infrastructure.domain.validation.BusinessValidator;
import com.rafatech.personcontactapi.infrastructure.domain.validation.NotInFuture;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

import static com.rafatech.personcontactapi.infrastructure.exception.ExceptionMessagesDictionary.*;
import static java.util.Objects.nonNull;

public class CreateOrUpdatePersonData {

    @NotBlank(message = PERSON_NAME_REQUIRED)
    private final String name;

    @NotNull(message = PERSON_CPF_REQUIRED)
    @CPF(message = PERSON_CPF_INVALID)
    private final String cpf;

    @NotNull(message = PERSON_BIRTH_DATE_REQUIRED)
    @NotInFuture(message = PERSON_BIRTH_DATE_NOT_IN_FUTURE)
    private final LocalDate birthDate;


    public CreateOrUpdatePersonData(String name, String cpf, LocalDate birthDate) {
        this.name = nonNull(name) ? name.trim() : null;
        this.cpf = nonNull(cpf) ? cpf.replaceAll("[.\\-]", "").trim() : null;
        this.birthDate = birthDate;
        BusinessValidator.apply(this);
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdatePersonData that = (CreateOrUpdatePersonData) o;
        return name.equals(that.name) &&
                cpf.equals(that.cpf) &&
                birthDate.equals(that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, birthDate);
    }
}
