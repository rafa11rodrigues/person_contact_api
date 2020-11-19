package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.data_builder.PersonTestDataBuilder;
import com.rafatech.personcontactapi.infrastructure.exception.ConstraintViolationListException;
import org.junit.jupiter.api.Test;

import static com.rafatech.personcontactapi.data_builder.PersonTestDataBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class NewPersonCommandTest {

    @Test
    void constructor_whenHasEmptyName_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new NewPersonCommand("", CPF, BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Person name is required", exception.getMessage());
    }

    @Test
    void constructor_whenHasBlankName_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new NewPersonCommand("  ", CPF, BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Person name is required", exception.getMessage());
    }

    @Test
    void constructor_whenHasEmptyCPF_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new NewPersonCommand(NAME, "", BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("'' is not a valid CPF", exception.getMessage());
    }

    @Test
    void constructor_whenHasBlankCPF_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new NewPersonCommand(NAME, "  ", BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("'  ' is not a valid CPF", exception.getMessage());
    }

    @Test
    void constructor_whenHasInvalidCPF_throwException() {
        var invalidCpf = "12345678901";
        var exception = assertThrows(ConstraintViolationListException.class,
                () -> new NewPersonCommand(NAME, invalidCpf, BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals(String.format("'%s' is not a valid CPF", invalidCpf), exception.getMessage());
    }
}