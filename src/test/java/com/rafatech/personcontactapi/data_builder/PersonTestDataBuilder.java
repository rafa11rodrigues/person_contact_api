package com.rafatech.personcontactapi.data_builder;

import com.rafatech.personcontactapi.domain.person.Person;
import com.rafatech.personcontactapi.domain.person.command.NewPersonCommand;

import java.time.LocalDate;

public class PersonTestDataBuilder {

    public static final String NAME = "Fulano";
    public static final String CPF = "20871378035";
    public static final LocalDate BIRTH_DATE = LocalDate.of(2000, 1, 1);


    public static NewPersonCommand newPersonCommand() {
        return new NewPersonCommand(NAME, CPF, BIRTH_DATE);
    }

    public static Person person() {
        return Person.of(newPersonCommand());
    }
}
