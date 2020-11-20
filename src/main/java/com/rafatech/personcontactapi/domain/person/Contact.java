package com.rafatech.personcontactapi.domain.person;

import com.rafatech.personcontactapi.domain.person.command.CreateOrUpdateContactData;
import com.rafatech.personcontactapi.infrastructure.domain.EntityBase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact")
public class Contact extends EntityBase {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Person person;


    protected Contact() {}

    public static Contact of(CreateOrUpdateContactData data, Person person) {
        var contact = new Contact();
        contact.name = data.getName();
        contact.phone = data.getPhone();
        contact.email = data.getEmail();
        contact.person = person;
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Person getPerson() {
        return person;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) &&
                phone.equals(contact.phone) &&
                email.equals(contact.email) &&
                person.equals(contact.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, person);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", person=" + person +
                '}';
    }
}
