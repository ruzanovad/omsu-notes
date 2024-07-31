package com.ruska112;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class Person implements Serializable {
    private String firstName;
    private String lastName;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;

    @JsonCreator
    public Person(@JsonProperty(value = "firstName") String firstName, @JsonProperty(value = "lastName") String lastName, @JsonProperty(value = "birthday") LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
    }

    public Person(Person person) {
        setFirstName(person.getFirstName());
        setLastName(person.getLastName());
        setBirthday(LocalDate.from(person.getBirthday()));
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException();
        }
        this.lastName = lastName;
    }


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException();
        }
        this.birthday = birthday;
    }

    public static String getJSONStringFromPerson(Person person) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(person);
    }

    public static Person getPersonFromJSONString(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Person.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday);
    }
}
