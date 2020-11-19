package com.rafatech.personcontactapi.port.rest.resource;

import com.rafatech.personcontactapi.port.rest.response.PersonFilteredResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/people")
public class PersonResource {

    @GetMapping
    public Page<PersonFilteredResponse> filter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false)LocalDate birthDateStart,
            @RequestParam(required = false)LocalDate birthDateEnd
            ) {
        return null;
    }
}
