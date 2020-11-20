package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.infrastructure.exception.ConstraintViolationListException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.rafatech.personcontactapi.data_builder.PersonTestDataBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateOrUpdatePersonDataTest {

    @Test
    void constructor_whenHasEmptyName_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new CreateOrUpdatePersonData("", CPF, BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Person name is required", exception.getMessage());
    }

    @Test
    void constructor_whenHasBlankName_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new CreateOrUpdatePersonData("  ", CPF, BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Person name is required", exception.getMessage());
    }

    @Test
    void constructor_whenHasEmptyCPF_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new CreateOrUpdatePersonData(NAME, "", BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("'' is not a valid CPF", exception.getMessage());
    }

    @Test
    void constructor_whenHasBlankCPF_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class, () -> new CreateOrUpdatePersonData(NAME, "  ", BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals("'' is not a valid CPF", exception.getMessage());
    }

    @Test
    void constructor_whenHasInvalidCPF_throwException() {
        var invalidCpf = "12345678901";
        var exception = assertThrows(ConstraintViolationListException.class,
                () -> new CreateOrUpdatePersonData(NAME, invalidCpf, BIRTH_DATE));
        assertEquals(1, exception.getMessages().size());
        assertEquals(String.format("'%s' is not a valid CPF", invalidCpf), exception.getMessage());
    }

    @Test
    void constructor_whenHasNullBirthDate_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class,
                () -> new CreateOrUpdatePersonData(NAME, CPF, null));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Person birth date is required", exception.getMessage());
    }

    @Test
    void constructor_whenHasBirthDateInFuture_throwException() {
        var exception = assertThrows(ConstraintViolationListException.class,
                () -> new CreateOrUpdatePersonData(NAME, CPF, LocalDate.now().plusDays(1)));
        assertEquals(1, exception.getMessages().size());
        assertEquals("Person birth date cannot be in future", exception.getMessage());
    }

    @Test
    void constructor_noErrors_buildObject() {
        var command = new CreateOrUpdatePersonData(NAME, CPF, BIRTH_DATE);
        assertEquals(NAME, command.getName());
        assertEquals(CPF, command.getCpf());
        assertEquals(BIRTH_DATE, command.getBirthDate());
    }

    @Test
    void constructor_noErrorsAndWithSpacesAndCPFDots_removeUndesiredCharsAndBuildObject() {
        var command = new CreateOrUpdatePersonData("  NAME ", "208.713.780-35", BIRTH_DATE);
        assertEquals("NAME", command.getName());
        assertEquals("20871378035", command.getCpf());
        assertEquals(BIRTH_DATE, command.getBirthDate());
    }
}