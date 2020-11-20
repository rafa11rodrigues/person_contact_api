package com.rafatech.personcontactapi.domain.person.command;

import java.util.Objects;
import java.util.UUID;

public class DeletePersonCommand {

    private final UUID id;

    public DeletePersonCommand(UUID id) {
        this.id = id;
    }


    public UUID getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletePersonCommand that = (DeletePersonCommand) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
