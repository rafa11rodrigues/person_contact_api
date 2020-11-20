package com.rafatech.personcontactapi.domain.person.command;

import java.util.UUID;

public class UpdatePersonCommand {

    private final UUID id;

    private final CreateOrUpdatePersonData data;

    public UpdatePersonCommand(UUID id, CreateOrUpdatePersonData data) {
        this.id = id;
        this.data = data;
    }


    public UUID getId() {
        return id;
    }

    public CreateOrUpdatePersonData getData() {
        return data;
    }
}
