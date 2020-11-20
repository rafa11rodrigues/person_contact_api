package com.rafatech.personcontactapi.domain.person.service;

import com.rafatech.personcontactapi.domain.person.Contact;
import com.rafatech.personcontactapi.domain.person.Person;
import com.rafatech.personcontactapi.domain.person.command.*;
import com.rafatech.personcontactapi.domain.person.repository.ContactRepository;
import com.rafatech.personcontactapi.domain.person.repository.PersonRepository;
import com.rafatech.personcontactapi.infrastructure.exception.BusinessException;
import com.rafatech.personcontactapi.infrastructure.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.rafatech.personcontactapi.infrastructure.exception.ExceptionMessagesDictionary.*;

@Service
@Transactional
public class PersonCRUDService {

    private final PersonRepository personRepository;

    private final ContactRepository contactRepository;

    public PersonCRUDService(PersonRepository personRepository, ContactRepository contactRepository) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
    }


    public Page<Person> filter(FilterPersonCommand command) {
        return personRepository.findAll(command.getFilter(), command.getPageable());
    }

    public Person get(GetPersonCommand command) {
        return personRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(PERSON_NOT_FIND_BY_ID, command.getId()));
    }

    public Person create(CreatePersonCommand command) {
        var person = Person.of(command);
        try{
            return personRepository.saveAndFlush(person);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException(PERSON_CPF_ALREADY_EXISTS, command.getPersonData().getCpf());
        }
    }

    public Person update(UpdatePersonCommand command) {
        var person = get(new GetPersonCommand(command.getId()));
        person.update(command.getData());
        try {
            return personRepository.saveAndFlush(person);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException(PERSON_CPF_ALREADY_EXISTS, command.getData().getCpf());
        }
    }

    public void delete(DeletePersonCommand command) {
        var person = get(new GetPersonCommand(command.getId()));
        personRepository.delete(person);
    }

    public Person addContact(AddContactCommand contactCommand) {
        var person = get(new GetPersonCommand(contactCommand.getPersonId()));
        var contact = Contact.of(contactCommand.getContactData(), person);
        person.addContact(contact);
        return personRepository.save(person);
    }

    public void removeContact(RemoveContactCommand command) {
        var person = get(new GetPersonCommand(command.getPersonId()));
        var contact = person.getContact(command.getContactId())
                .orElseThrow(() -> new NotFoundException(CONTACT_NOT_BELONGS_TO_PERSON,
                        command.getContactId(),
                        command.getPersonId()));
        person.removeContact(contact);
        if (person.getContacts().isEmpty()) {
            throw new BusinessException(PERSON_CONTACTS_NOT_EMPTY);
        }
        contactRepository.delete(contact);
        personRepository.save(person);
    }
}
