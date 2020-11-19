package com.rafatech.personcontactapi.domain.person.filter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.rafatech.personcontactapi.domain.person.QPerson;
import com.rafatech.personcontactapi.infrastructure.domain.Filter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

import static com.rafatech.personcontactapi.domain.person.QPerson.person;

public class PersonFilter implements Filter {

    private String name;

    private String cpf;

    private LocalDate birthDateStart;

    private LocalDate birthDateEnd;

    private PersonFilter() {}

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Predicate toPredicate() {
        return new BooleanBuilder()
                .and(byName())
                .and(byCpf())
                .and(byBirthDateStart())
                .and(byBirthDateEnd())
                .getValue();
    }

    private BooleanExpression byName() {
        return StringUtils.hasText(name)
                ? person.name.containsIgnoreCase(name.trim())
                : null;
    }

    private BooleanExpression byCpf() {
        return StringUtils.hasText(cpf)
                ? person.cpf.containsIgnoreCase(cpf.trim())
                : null;
    }

    private BooleanExpression byBirthDateStart() {
        return Objects.nonNull(birthDateStart)
                ? person.birthDate.goe(birthDateStart)
                : null;
    }

    private BooleanExpression byBirthDateEnd() {
        return Objects.nonNull(birthDateEnd)
                ? person.birthDate.loe(birthDateEnd)
                : null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonFilter that = (PersonFilter) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(birthDateStart, that.birthDateStart) &&
                Objects.equals(birthDateEnd, that.birthDateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, birthDateStart, birthDateEnd);
    }


    public static class Builder {
        private PersonFilter filter;

        protected Builder() {
            this.filter = new PersonFilter();
        }

        public Builder name(String name) {
            this.filter.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            this.filter.cpf = cpf;
            return this;
        }

        public Builder birthDateStart(LocalDate birthDateStart) {
            this.filter.birthDateStart = birthDateStart;
            return this;
        }

        public Builder birthDateEnd(LocalDate birthDateEnd) {
            this.filter.birthDateEnd = birthDateEnd;
            return this;
        }

        public PersonFilter build() {
            return filter;
        }
    }
}
