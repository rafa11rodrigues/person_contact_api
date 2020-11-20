package com.rafatech.personcontactapi.port.rest.response;

import com.rafatech.personcontactapi.domain.person.Person;

import java.time.LocalDate;
import java.util.UUID;

public class PersonSimpleResponse {

    private UUID id;

    private String name;

    private String cpf;

    private LocalDate birthDate;

    protected PersonSimpleResponse() {}

    public PersonSimpleResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.cpf = person.getCpf();
        this.birthDate = person.getBirthDate();
    }


    public UUID getId() {
        return id;
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
