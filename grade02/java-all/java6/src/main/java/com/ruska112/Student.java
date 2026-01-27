package com.ruska112;

import java.util.Objects;

public class Student extends Human {
    protected String faculty;

    Student(String surname, String name, String fatherName, int age, String faculty) {
        super(surname, name, fatherName, age);
        setFaculty(faculty);
    }

    Student(Student copy) {
        super(copy.getSurname(), copy.getName(), copy.getFatherName(), copy.getAge());
        setFaculty(copy.getFaculty());
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        if (faculty == null) {
            throw new IllegalArgumentException();
        }
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(surname, student.surname) && Objects.equals(name, student.name) && Objects.equals(fatherName, student.fatherName) && Objects.equals(faculty, student.faculty) && age == student.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty);
    }
}
