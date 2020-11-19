package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.infrastructure.domain.BusinessValidator;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.rafatech.personcontactapi.infrastructure.exception.ExceptionMessagesDictionary.*;

public class NewPersonCommand {

    @NotBlank(message = PERSON_NAME_REQUIRED)
    private final String name;

    @CPF(message = PERSON_CPF_VALID)
    private final String cpf;

    @NotNull(message = PERSON_BIRTH_DATE_REQUIRED)
    private final LocalDate birthDate;


    public NewPersonCommand(String name, String cpf, LocalDate birthDate) {
        this.name = name;
        this.cpf = cpf;
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
}
