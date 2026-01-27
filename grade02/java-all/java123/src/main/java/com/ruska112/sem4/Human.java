package com.ruska112.sem4;

public class Human {
    private String fullName;
    private MyDate birthday;

    public Human(String fullName, MyDate birthday) {
        setFullName(fullName);
        setBirthday(birthday);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (!"".equals(fullName) && fullName != null) {
            this.fullName = fullName;
        } else {
            throw new IllegalArgumentException("Human setFullName: argument is empty!");
        }
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        if (birthday != null) {
            this.birthday = birthday;
        } else {
            throw new IllegalArgumentException("Human setBirthday: argument is null!");
        }
    }
}
