package com.rafatech.personcontactapi.port.rest.response;

import com.rafatech.personcontactapi.domain.person.Contact;

import java.time.LocalDate;
import java.util.UUID;

public class ContactResponse {

    private UUID id;

    private String name;

    private String phone;

    private String email;


    protected ContactResponse() {}

    public ContactResponse(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
