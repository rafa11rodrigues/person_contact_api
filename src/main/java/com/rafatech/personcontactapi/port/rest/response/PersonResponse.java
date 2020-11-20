package com.rafatech.personcontactapi.port.rest.response;

import com.rafatech.personcontactapi.domain.person.Person;
import com.rafatech.personcontactapi.port.rest.request.PersonRequest;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonResponse {

    private UUID id;

    private String name;

    private String cpf;

    private LocalDate birthDate;

    private Set<ContactResponse> contacts;


    public PersonResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.cpf = person.getCpf();
        this.birthDate = person.getBirthDate();
        this.contacts = person.getContacts().stream()
                .map(ContactResponse::new).collect(Collectors.toSet());
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

    public Set<ContactResponse> getContacts() {
        return contacts;
    }
}
