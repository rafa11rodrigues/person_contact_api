package com.rafatech.personcontactapi.domain.person.service;

import com.rafatech.personcontactapi.domain.person.Person;
import com.rafatech.personcontactapi.domain.person.command.FilterPersonCommand;
import com.rafatech.personcontactapi.domain.person.command.NewPersonCommand;
import com.rafatech.personcontactapi.domain.person.repository.PersonRepository;
import com.rafatech.personcontactapi.infrastructure.exception.BusinessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.rafatech.personcontactapi.infrastructure.exception.ExceptionMessagesDictionary.PERSON_CPF_ALREADY_EXISTS;

@Service
@Transactional
public class PersonCRUDService {

    private final PersonRepository personRepository;

    public PersonCRUDService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Page<Person> filter(FilterPersonCommand command) {
        return personRepository.findAll(command.getFilter(), command.getPageable());
    }

    public Person create(NewPersonCommand command) {
        var person = Person.of(command);
        try{
            return personRepository.saveAndFlush(person);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException(PERSON_CPF_ALREADY_EXISTS, command.getCpf());
        }
    }
}
