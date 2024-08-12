package com.ruska112;

import java.util.Objects;

public class Human {
    private String lastName;
    private String firstName;

    private String patronymic;

    private int age;

    private Gender gender;

    public Human(String lastName, String firstName, String patronymic, int age, Gender gender) {
        setLastName(lastName);
        setFirstName(firstName);
        setPatronymic(patronymic);
        setAge(age);
        setGender(gender);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        if (patronymic == null) {
            throw new IllegalArgumentException();
        }
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException();
        }
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && lastName.equals(human.lastName) && firstName.equals(human.firstName) && patronymic.equals(human.patronymic) && gender == human.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, patronymic, age, gender);
    }
}
