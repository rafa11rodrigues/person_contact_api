package com.rafatech.personcontactapi.infrastructure.domain;

import com.querydsl.core.types.Predicate;

public interface Filter {

    Predicate toPredicate();
}
