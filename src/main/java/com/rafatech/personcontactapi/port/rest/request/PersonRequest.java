package com.rafatech.personcontactapi.port.rest.request;

import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdatePersonData;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonRequest {

    private String name;

    private String cpf;

    private LocalDate birthDate;

    private Set<ContactRequest> contacts = new HashSet<>();

    protected PersonRequest() {}


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

    public Set<ContactRequest> getContacts() {
        return contacts;
    }
}
