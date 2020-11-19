package com.rafatech.personcontactapi.domain.person.repository;

import com.rafatech.personcontactapi.domain.person.filter.PersonFilter;
import com.rafatech.personcontactapi.integration_tests.PersonIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonRepositoryIT extends PersonIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    private Pageable pageable = PageRequest.of(0, 2);


    @Test
    void findAll_withEmptyFilter_returnAllPeople() {
        var filter = PersonFilter.builder().build();
        var people = personRepository.findAll(filter, pageable);
        assertEquals(2, people.getTotalElements());
    }

    @Test
    void findAll_pageSizeSmallerThanTotalElements_returnResultsInDifferentPages() {
        var filter = PersonFilter.builder().build();
        var pageable = PageRequest.of(0, 1);
        var people = personRepository.findAll(filter, pageable);
        assertEquals(2, people.getTotalElements());
        assertEquals(1, people.getNumberOfElements());
    }

    @Test
    void findAll_whenFilterHasName_returnOnlyPeopleWhoseNameMatches() {
        var filter = PersonFilter.builder().name("fOo ").build();
        var people = personRepository.findAll(filter, pageable);
        assertEquals(1, people.getTotalElements());
        assertEquals("Foo Person", people.getContent().get(0).getName());
    }

    @Test
    void findAll_filterWithCPF_returnOnlyPeopleWhoseCPFMatches() {
        var filter = PersonFilter.builder().cpf("1209").build();
        var people = personRepository.findAll(filter, pageable);
        assertEquals(1, people.getTotalElements());
        assertEquals("91209040026", people.getContent().get(0).getCpf());
    }

    @Test
    void findAll_filterWithBirthDateStart_returnOnlyPeopleWhoseBirthDateStartsInGivenDate() {
        var filter = PersonFilter.builder()
                .birthDateStart(LocalDate.of(1997, 9, 20))
                .build();
        var people = personRepository.findAll(filter, pageable);
        assertEquals(1, people.getTotalElements());
        assertEquals(LocalDate.of(2000, 7, 8), people.getContent().get(0).getBirthDate());
    }

    @Test
    void findAll_filterWithBirthDateEnd_returnOnlyPeopleWhoseBirthDateIsUpToGivenDate() {
        var filter = PersonFilter.builder()
                .birthDateEnd(LocalDate.of(1997, 9, 20))
                .build();
        var people = personRepository.findAll(filter, pageable);
        assertEquals(1, people.getTotalElements());
        assertEquals(LocalDate.of(1990, 1, 1), people.getContent().get(0).getBirthDate());
    }

    @Test
    void findAll_filterWithMoreThanOneParameter_returnOnlyPeopleWhoMatchFilter() {
        var filter = PersonFilter.builder()
                .name("bA")
                .cpf("12")
                .build();
        var people = personRepository.findAll(filter, pageable);
        assertEquals(1, people.getTotalElements());
        assertEquals("Bar Person", people.getContent().get(0).getName());
    }
}