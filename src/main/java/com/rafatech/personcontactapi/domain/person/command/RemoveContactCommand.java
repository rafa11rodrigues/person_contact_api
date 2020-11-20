package com.rafatech.personcontactapi.domain.person.command;

import java.util.Objects;
import java.util.UUID;

public class RemoveContactCommand {

    private final UUID personId;

    private final UUID contactId;


    public RemoveContactCommand(UUID personId, UUID contactId) {
        this.personId = personId;
        this.contactId = contactId;
    }


    public UUID getPersonId() {
        return personId;
    }

    public UUID getContactId() {
        return contactId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveContactCommand that = (RemoveContactCommand) o;
        return personId.equals(that.personId) &&
                contactId.equals(that.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, contactId);
    }
}
