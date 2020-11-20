package com.rafatech.personcontactapi.domain.person.command;

import java.util.UUID;

public class AddContactCommand {

    private final UUID personId;

    private final CreateOrUpdateContactData contactData;


    public AddContactCommand(UUID personId, CreateOrUpdateContactData contactData) {
        this.personId = personId;
        this.contactData = contactData;
    }


    public UUID getPersonId() {
        return personId;
    }

    public CreateOrUpdateContactData getContactData() {
        return contactData;
    }
}
