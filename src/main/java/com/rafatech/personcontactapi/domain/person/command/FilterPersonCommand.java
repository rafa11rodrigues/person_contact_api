package com.rafatech.personcontactapi.domain.person.command;

import com.rafatech.personcontactapi.domain.person.filter.PersonFilter;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

public class FilterPersonCommand {

    private final PersonFilter filter;

    private final Pageable pageable;

    public FilterPersonCommand(PersonFilter filter, Pageable pageable) {
        this.filter = filter;
        this.pageable = pageable;
    }

    public PersonFilter getFilter() {
        return filter;
    }

    public Pageable getPageable() {
        return pageable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterPersonCommand that = (FilterPersonCommand) o;
        return filter.equals(that.filter) &&
                pageable.equals(that.pageable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter, pageable);
    }
}
