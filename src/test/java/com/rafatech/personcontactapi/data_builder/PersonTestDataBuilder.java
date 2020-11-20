package com.rafatech.personcontactapi.data_builder;

import com.rafatech.personcontactapi.domain.person.Person;
import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdateContactData;
import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdatePersonData;
import com.rafatech.personcontactapi.domain.person.command.CreatePersonCommand;

import java.time.LocalDate;
import java.util.Set;

import static com.rafatech.personcontactapi.data_builder.ContactTestDataBuilder.createOrUpdateContactData;

public class PersonTestDataBuilder {

    public static final String NAME = "Fulano";
    public static final String CPF = "20871378035";
    public static final LocalDate BIRTH_DATE = LocalDate.of(2000, 1, 1);
    public static final Set<CreateOrUpdateContactData> CONTACTS = Set.of(createOrUpdateContactData());


    public static CreateOrUpdatePersonData createOrUpdatePersonData() {
        return new CreateOrUpdatePersonData(NAME, CPF, BIRTH_DATE);
    }

    public static CreatePersonCommand createPersonCommand() {
        var personData = createOrUpdatePersonData();
        return new CreatePersonCommand(personData, CONTACTS);
    }

    public static Person person() {
        return Person.of(createPersonCommand());
    }
}
