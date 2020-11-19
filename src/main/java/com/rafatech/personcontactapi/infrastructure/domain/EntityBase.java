package com.rafatech.personcontactapi.infrastructure.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class EntityBase {

    @Id
    private UUID id = UUID.randomUUID();


    public UUID getId() {
        return id;
    }
}
