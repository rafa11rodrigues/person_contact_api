package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.infrastructure.exception.ConstraintViolationListException;
import org.junit.jupiter.api.Test;

import static com.rafatech.personcontactapi.data_builder.ContactTestDataBuilder.NAME;
import static com.rafatech.personcontactapi.data_builder.ContactTestDataBuilder.PHONE;
import static org.junit.jupiter.api.Assertions.*;

class CreateOrUpdateContactDataTest {

    @Test
    void constructor_nullEmail_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class,
                () -> new CreateOrUpdateContactData(NAME, PHONE, null));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Contact email is required", exception.getMessage());
    }

    @Test
    void constructor_blankEmail_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class,
                () -> new CreateOrUpdateContactData(NAME, PHONE, " "));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Contact email is required", exception.getMessage());
    }

    @Test
    void constructor_invalidEmail_throwException() {
        var invalidEmail = "email@";
        var exception = assertThrows(ConstraintViolationListException.class,
                () -> new CreateOrUpdateContactData(NAME, PHONE, invalidEmail));
        assertEquals(1, exception.getMessages().size());
        assertEquals(String.format("'%s' is not a valid email", invalidEmail), exception.getMessage());
    }

    @Test
    void constructor_replaceUndesiredCharacters() {
        var command = new CreateOrUpdateContactData(" Name  ", "(44)  12345-6789  ", "  some@email.com");
        assertEquals("Name", command.getName());
        assertEquals("44123456789", command.getPhone());
        assertEquals("some@email.com", command.getEmail());
    }
}