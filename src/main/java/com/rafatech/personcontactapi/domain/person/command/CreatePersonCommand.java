package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.infrastructure.domain.validation.BusinessValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

import static com.rafatech.personcontactapi.infrastructure.exception.ExceptionMessagesDictionary.PERSON_CONTACTS_NOT_EMPTY;

public class CreatePersonCommand {

    @NotNull(message = "Person data is required")
    @Valid
    private final CreateOrUpdatePersonData personData;

    @NotEmpty(message = PERSON_CONTACTS_NOT_EMPTY)
    @Valid
    private final Set<CreateOrUpdateContactData> contactData;

    public CreatePersonCommand(CreateOrUpdatePersonData personData, Set<CreateOrUpdateContactData> contactData) {
        this.personData = personData;
        this.contactData = contactData;
        BusinessValidator.apply(this);
    }


    public CreateOrUpdatePersonData getPersonData() {
        return personData;
    }

    public Set<CreateOrUpdateContactData> getContactData() {
        return contactData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonCommand that = (CreatePersonCommand) o;
        return personData.equals(that.personData) &&
                contactData.equals(that.contactData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personData, contactData);
    }
}
