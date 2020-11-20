package com.rafatech.personcontactapi.port.rest.request;

import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdatePersonData;

import java.time.LocalDate;

public class PersonSimpleRequestBody {

    private String name;

    private String cpf;

    private LocalDate birthDate;


    protected PersonSimpleRequestBody() {}


    public CreateOrUpdatePersonData toCreateOrUpdateData() {
        return new CreateOrUpdatePersonData(name, cpf, birthDate);
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
