package com.rafatech.personcontactapi.infrastructure.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.*;

public interface EntityBaseRepository<T extends EntityBase> extends JpaRepository<T, UUID>, QuerydslPredicateExecutor<T> {

    default Page<T> findAll(Filter filter, Pageable pageable) {
        var predicate = filter.toPredicate();
        return Objects.isNull(predicate)
                ? findAll(pageable)
                : findAll(predicate, pageable);
    }
}
