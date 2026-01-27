package com.ruska112;

import java.time.LocalDate;

public class Student extends Human {
    private String faculty;

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        if (faculty == null) {
            throw new IllegalArgumentException();
        }
        this.faculty = faculty;
    }

    public Student(String firstName, String lastName, LocalDate birthday, String faculty) {
        super(firstName, lastName, birthday);
        setFaculty(faculty);
    }
}
