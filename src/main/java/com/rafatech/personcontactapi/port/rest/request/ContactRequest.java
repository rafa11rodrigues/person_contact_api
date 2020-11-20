package com.rafatech.personcontactapi.port.rest.request;

import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdateContactData;

public class ContactRequest {

    private String name;

    private String phone;

    private String email;

    protected ContactRequest() {}


    public CreateOrUpdateContactData toCreateOrUpdateContactData() {
        return new CreateOrUpdateContactData(name, phone, email);
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
