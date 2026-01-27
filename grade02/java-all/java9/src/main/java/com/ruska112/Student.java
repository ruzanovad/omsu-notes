package com.ruska112;

import java.util.Objects;

public class Student extends Human {
    private String university;
    private String faculty;
    private String speciality;

    public Student(String lastName, String firstName, String patronymic, int age, Gender gender, String university, String faculty, String speciality) {
        super(lastName, firstName, patronymic, age, gender);
        setUniversity(university);
        setFaculty(faculty);
        setSpeciality(speciality);
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        if (university == null) {
            throw new IllegalArgumentException();
        }
        this.university = university;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        if (speciality == null) {
            throw new IllegalArgumentException();
        }
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return university.equals(student.university) && faculty.equals(student.faculty) && speciality.equals(student.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), university, faculty, speciality);
    }
}
