package com.rafatech.personcontactapi.domain.person;

import com.rafatech.personcontactapi.data_builder.PersonTestDataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void of_createNewPerson() {
        var command = PersonTestDataBuilder.createPersonCommand();

        var person = Person.of(command);
        assertNotNull(person.getId());
        assertEquals(command.getPersonData().getName(), person.getName());
        assertEquals(command.getPersonData().getCpf(), person.getCpf());
        assertEquals(command.getPersonData().getBirthDate(), person.getBirthDate());
        assertEquals(1, person.getContacts().size());
    }
}