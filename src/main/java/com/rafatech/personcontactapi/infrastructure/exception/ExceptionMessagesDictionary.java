package com.rafatech.personcontactapi.infrastructure.exception;

public class ExceptionMessagesDictionary {

    public static final String PERSON_NAME_REQUIRED = "person.name.required";
    public static final String PERSON_CPF_REQUIRED = "person.cpf.required";
    public static final String PERSON_CPF_INVALID = "person.cpf.invalid";
    public static final String PERSON_CPF_ALREADY_EXISTS = "person.cpf.already_exists";
    public static final String PERSON_BIRTH_DATE_REQUIRED = "person.birth_date.required";
    public static final String PERSON_BIRTH_DATE_NOT_IN_FUTURE = "person.birth_date.not_in_future";
    public static final String PERSON_CONTACTS_NOT_EMPTY = "person.contacts.not_empty";

    public static final String PERSON_NOT_FIND_BY_ID = "person.not_found.by_id";


    public static final String CONTACT_NAME_REQUIRED = "contact.name.required";
    public static final String CONTACT_PHONE_REQUIRED = "contact.phone.required";
    public static final String CONTACT_EMAIL_REQUIRED = "contact.email.required";
    public static final String CONTACT_EMAIL_INVALID = "contact.email.invalid";

    public static final String CONTACT_NOT_BELONGS_TO_PERSON = "contact.not_belongs.to_person";
}
