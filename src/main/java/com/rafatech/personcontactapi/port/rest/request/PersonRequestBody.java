package com.rafatech.personcontactapi.port.rest.request;

import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdatePersonData;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PersonRequestBody {

    private String name;

    private String cpf;

    private LocalDate birthDate;

    private Set<ContactRequestBody> contacts;

    protected PersonRequestBody() {}


    public CreateOrUpdatePersonData toCreateOrUpdateCommandData() {
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

    public Set<ContactRequestBody> getContacts() {
        return Objects.nonNull(contacts) ? contacts : new HashSet<>();
    }
}
