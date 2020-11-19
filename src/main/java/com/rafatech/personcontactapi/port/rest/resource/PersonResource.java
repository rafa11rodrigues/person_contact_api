package com.rafatech.personcontactapi.port.rest.resource;

import com.rafatech.personcontactapi.domain.person.command.FilterPersonCommand;
import com.rafatech.personcontactapi.domain.person.filter.PersonFilter;
import com.rafatech.personcontactapi.domain.person.service.PersonCRUDService;
import com.rafatech.personcontactapi.port.rest.response.PersonFilteredResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/people")
public class PersonResource {

    private final PersonCRUDService personCRUDService;

    public PersonResource(PersonCRUDService personCRUDService) {
        this.personCRUDService = personCRUDService;
    }


    @GetMapping
    public Page<PersonFilteredResponse> filter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false)LocalDate birthDateStart,
            @RequestParam(required = false)LocalDate birthDateEnd,
            Pageable pageable
    ) {
        var filter = PersonFilter.builder()
                .name(name)
                .cpf(cpf)
                .birthDateStart(birthDateStart)
                .birthDateEnd(birthDateEnd)
                .build();
        var command = new FilterPersonCommand(filter, pageable);
        var filteredPeople = personCRUDService.filter(command);
        return filteredPeople.map(PersonFilteredResponse::new);
    }
}
