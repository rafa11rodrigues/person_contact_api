package com.rafatech.personcontactapi.domain.person;

import com.rafatech.personcontactapi.domain.person.command.NewPersonCommand;
import com.rafatech.personcontactapi.infrastructure.domain.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person extends EntityBase {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;


    protected Person() {}

    public static Person of(NewPersonCommand command) {
        var person = new Person();
        person.name = command.getName();
        person.cpf = command.getCpf();
        person.birthDate = command.getBirthDate();
        return person;
    }


    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return cpf.equals(person.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
