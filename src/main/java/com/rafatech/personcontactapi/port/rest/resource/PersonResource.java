package com.rafatech.personcontactapi.port.rest.resource;

import com.rafatech.personcontactapi.domain.person.Contact;
import com.rafatech.personcontactapi.domain.person.command.*;
import com.rafatech.personcontactapi.domain.person.filter.PersonFilter;
import com.rafatech.personcontactapi.domain.person.service.PersonCRUDService;
import com.rafatech.personcontactapi.port.rest.request.ContactRequest;
import com.rafatech.personcontactapi.port.rest.request.PersonRequest;
import com.rafatech.personcontactapi.port.rest.response.ContactResponse;
import com.rafatech.personcontactapi.port.rest.response.PersonResponse;
import com.rafatech.personcontactapi.port.rest.response.PersonSimpleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PersonResource {

    private final PersonCRUDService personCRUDService;

    public PersonResource(PersonCRUDService personCRUDService) {
        this.personCRUDService = personCRUDService;
    }


    @GetMapping
    public Page<PersonSimpleResponse> filter(
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
        return filteredPeople.map(PersonSimpleResponse::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse create(@RequestBody PersonRequest request) {
        var personData = request.toCreateOrUpdateCommandData();
        var contactData = request.getContacts().stream()
                .map(ContactRequest::toCreateOrUpdateContactData)
                .collect(Collectors.toSet());
        var command = new CreatePersonCommand(personData, contactData);
        var createdPerson = personCRUDService.create(command);
        return new PersonResponse(createdPerson);
    }

    @GetMapping("/{id}")
    public PersonResponse get(@PathVariable UUID id) {
        var person = personCRUDService.get(new GetPersonCommand(id));
        return new PersonResponse(person);
    }

    @PutMapping("/{id}")
    public PersonResponse update(@PathVariable UUID id, @RequestBody PersonRequest request) {
        var command = new UpdatePersonCommand(id, request.toCreateOrUpdateCommandData());
        var person = personCRUDService.update(command);
        return new PersonResponse(person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        personCRUDService.delete(new DeletePersonCommand(id));
    }

    @PostMapping("/{personId}/contacts")
    public PersonResponse addContact(@PathVariable UUID personId, @RequestBody ContactRequest request) {
        var command = new AddContactCommand(personId, request.toCreateOrUpdateContactData());
        var person = personCRUDService.addContact(command);
        return new PersonResponse(person);
    }

    @DeleteMapping("/{personId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeContact(@PathVariable UUID personId, @PathVariable UUID contactId) {
        var command = new RemoveContactCommand(personId, contactId);
        personCRUDService.removeContact(command);
    }
}
