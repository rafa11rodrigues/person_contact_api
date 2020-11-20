package com.rafatech.personcontactapi.domain.person.command;

import java.util.Objects;
import java.util.UUID;

public class GetPersonCommand {

    private final UUID id;

    public GetPersonCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPersonCommand that = (GetPersonCommand) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
