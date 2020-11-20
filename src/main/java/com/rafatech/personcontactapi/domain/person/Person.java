package com.rafatech.personcontactapi.domain.person;

import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdatePersonData;
import com.rafatech.personcontactapi.domain.person.command.CreatePersonCommand;
import com.rafatech.personcontactapi.infrastructure.domain.EntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "person")
public class Person extends EntityBase {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @OrderBy("name")
    private Set<Contact> contacts = new HashSet<>();


    protected Person() {}

    public static Person of(CreatePersonCommand command) {
        var person = new Person();
        person.update(command.getPersonData());
        command.getContactData().stream()
                .map(contactData -> Contact.of(contactData, person))
                .forEach(person::addContact);
        return person;
    }

    public void update(CreateOrUpdatePersonData data) {
        this.name = data.getName();
        this.cpf = data.getCpf();
        this.birthDate = data.getBirthDate();
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    public Optional<Contact> getContact(UUID contactId) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(contactId))
                .findFirst();
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

    public Set<Contact> getContacts() {
        return contacts;
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
