package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.infrastructure.domain.validation.BusinessValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.rafatech.personcontactapi.infrastructure.exception.ExceptionMessagesDictionary.*;
import static java.util.Objects.*;

public class CreateOrUpdateContactData {

    @NotBlank(message = CONTACT_NAME_REQUIRED)
    private final String name;

    @NotBlank(message = CONTACT_PHONE_REQUIRED)
    private final String phone;

    @NotBlank(message = CONTACT_EMAIL_REQUIRED)
    @Email(message = CONTACT_EMAIL_INVALID)
    private final String email;

    public CreateOrUpdateContactData(String name, String phone, String email) {
        this.name = nonNull(name) ? name.trim() : null;
        this.phone = nonNull(phone) ? phone.replaceAll("[()\\- ]", "").trim() : null;
        this.email = nonNull(email) ? email.trim() : null;
        BusinessValidator.apply(this);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrUpdateContactData that = (CreateOrUpdateContactData) o;
        return name.equals(that.name) &&
                phone.equals(that.phone) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return hash(name, phone, email);
    }
}
