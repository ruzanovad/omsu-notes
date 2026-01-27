package com.ruska112;

import java.util.Objects;

public class Human {
    protected String surname;
    protected String name;
    protected String fatherName;

    protected int age;

    public void setSurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException();
        }
        this.surname = surname;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public void setFatherName(String fatherName) {
        if (fatherName == null) {
            throw new IllegalArgumentException();
        }
        this.fatherName = fatherName;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public int getAge() {
        return age;
    }

    Human(String surname, String name, String fatherName, int age) {
        setSurname(surname);
        setName(name);
        setFatherName(fatherName);
        setAge(age);
    }

    Human(Human copy) {
        this.surname = copy.getSurname();
        this.name = copy.getName();
        this.fatherName = copy.getFatherName();
        this.age = copy.getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(surname, human.surname) && Objects.equals(name, human.name) && Objects.equals(fatherName, human.fatherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, fatherName, age);
    }


}
