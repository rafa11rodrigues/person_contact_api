package com.rafatech.personcontactapi.domain.person.service;

import com.rafatech.personcontactapi.data_builder.PersonTestDataBuilder;
import com.rafatech.personcontactapi.domain.person.Person;
import com.rafatech.personcontactapi.domain.person.command.FilterPersonCommand;
import com.rafatech.personcontactapi.domain.person.filter.PersonFilter;
import com.rafatech.personcontactapi.domain.person.repository.PersonRepository;
import com.rafatech.personcontactapi.infrastructure.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PersonCRUDServiceTest {

    @InjectMocks
    private PersonCRUDService personCRUDService;

    @Mock
    private PersonRepository personRepository;


    @Test
    void filter_returnPeopleFound() {
        var filter = PersonFilter.builder().build();
        var pageable = PageRequest.of(0, 2);
        var command = new FilterPersonCommand(filter, pageable);
        var person = PersonTestDataBuilder.person();

        when(personRepository.findAll(filter, pageable))
                .thenReturn(new PageImpl<>(List.of(person), pageable, 1));

        var filteredPeople = personCRUDService.filter(command);
        assertEquals(1, filteredPeople.getTotalElements());
        assertEquals(person, filteredPeople.getContent().get(0));
    }

    @Test
    void create_duplicatedCPF_throwException() {
        var command = PersonTestDataBuilder.newPersonCommand();
        var personCaptor = ArgumentCaptor.forClass(Person.class);
        when(personRepository.saveAndFlush(personCaptor.capture()))
                .thenThrow(new DataIntegrityViolationException(""));

        var exception = assertThrows(BusinessException.class,
                () -> personCRUDService.create(command));
        assertEquals(String.format("CPF '%s' already assigned to a person", command.getCpf())
                , exception.getMessage());
        var capturedPerson = personCaptor.getValue();
        verify(personRepository).saveAndFlush(capturedPerson);
        assertEquals(command.getName(), capturedPerson.getName());
    }

    @Test
    void create_everythingOk_createAndSavePerson() {
        var command = PersonTestDataBuilder.newPersonCommand();
        var personCaptor = ArgumentCaptor.forClass(Person.class);
        var person = PersonTestDataBuilder.person();
        when(personRepository.saveAndFlush(personCaptor.capture()))
                .thenReturn(person);

        var returnedPerson = personCRUDService.create(command);
        var capturedPerson = personCaptor.getValue();
        verify(personRepository).saveAndFlush(capturedPerson);
        assertEquals(person, returnedPerson);
        assertEquals(command.getName(), capturedPerson.getName());
    }
}