package com.rafatech.personcontactapi.data_builder;

import com.rafatech.personcontactapi.domain.person.Contact;
import com.rafatech.personcontactapi.domain.person.Person;
import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdateContactData;

public class ContactTestDataBuilder {

    public static final String NAME = "Foolano";

    public static final String PHONE = "44981234567";

    public static final String EMAIL = "foolano@gmail.com";


    public static CreateOrUpdateContactData createOrUpdateContactData() {
        return new CreateOrUpdateContactData(NAME, PHONE, EMAIL);
    }

    public static Contact contact(Person person) {
        return Contact.of(createOrUpdateContactData(), person);
    }
}
