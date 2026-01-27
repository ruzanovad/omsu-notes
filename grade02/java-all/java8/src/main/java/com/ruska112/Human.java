package com.ruska112;

import java.time.LocalDate;
import java.util.Objects;

public class Human {
    private String firstName;
    private String lastName;

    private LocalDate birthday;

    public Human(String firstName, String lastName, LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
    }

    public Human(Human person) {
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

    protected int setName() {
        return 0;
    }

    public static void getName() {
        return;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(firstName, human.firstName) && Objects.equals(lastName, human.lastName) && Objects.equals(birthday, human.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday);
    }
}
