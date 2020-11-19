package com.rafatech.personcontactapi.domain.person;

import com.rafatech.personcontactapi.data_builder.PersonTestDataBuilder;
import com.rafatech.personcontactapi.domain.person.command.NewPersonCommand;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void of_createNewPerson() {
        var command = PersonTestDataBuilder.newPersonCommand();

        var person = Person.of(command);
        assertNotNull(person.getId());
        assertEquals(command.getName(), person.getName());
        assertEquals(command.getCpf(), person.getCpf());
        assertEquals(command.getBirthDate(), person.getBirthDate());
    }
}