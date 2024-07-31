package com.ruska112;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PhoneBookTest {

    PhoneBook phoneBook = new PhoneBook();

    PhoneBook result = new PhoneBook();
    Human human0;
    Human human1;
    Human human2;

    ArrayList<String> phones0;
    ArrayList<String> phones1;
    ArrayList<String> phones2;

    @Before
    public void setUp() {
        human0 = new Human("Blin", "0", "0", 0);
        human1 = new Human("Smetana", "1", "1", 1);
        human2 = new Human("Bloon", "2", "2", 2);

        phones0 = new ArrayList<>();
        phones0.add("1120");
        phones0.add("1121");
        phones0.add("1122");

        phones1 = new ArrayList<>();
        phones1.add("2120");
        phones1.add("2121");
        phones1.add("2122");

        phones2 = new ArrayList<>();
        phones2.add("3120");
        phones2.add("3121");
        phones2.add("3122");

        phoneBook = new PhoneBook();
        phoneBook.addPhone(human0, "1120");
        phoneBook.addPhone(human0, "1121");
        phoneBook.addPhone(human0, "1122");

        phoneBook.addPhone(human1, "2120");
        phoneBook.addPhone(human1, "2121");
        phoneBook.addPhone(human1, "2122");

        phoneBook.addPhone(human2, "3120");
        phoneBook.addPhone(human2, "3121");
        phoneBook.addPhone(human2, "3122");

        result.addPhone(human0, "1120");
        result.addPhone(human0, "1121");
        result.addPhone(human0, "1122");

        result.addPhone(human2, "3120");
        result.addPhone(human2, "3121");
        result.addPhone(human2, "3122");
    }

    @Test
    public void addPhoneTest0() {
        phones0.add("1123");
        phoneBook.addPhone(human0, "1123");
        assertArrayEquals(phones0.toArray(), phoneBook.getAllPhone(human0).toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPhoneTest1() {
        phoneBook.addPhone(new Human("9", "9", "9", 9), "1120");
    }

    @Test
    public void removePhoneTest0() {
        phones0.remove("1122");
        phoneBook.removePhone(human0, "1122");
        assertArrayEquals(phones0.toArray(), phoneBook.getAllPhone(human0).toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePhoneTest1() {
        phoneBook.removePhone(human0, "1120");
        phoneBook.removePhone(human0, "1121");
        phoneBook.removePhone(human0, "1122");
        phoneBook.getAllPhone(human0);
    }

    @Test
    public void getAllPhoneTest0() {
        assertArrayEquals(phones0.toArray(), phoneBook.getAllPhone(human0).toArray());
    }

    @Test
    public void getAllPhoneTest1() {
        assertArrayEquals(phones1.toArray(), phoneBook.getAllPhone(human1).toArray());
    }

    @Test
    public void getHumanWithPhoneTest0() {
        assertEquals(human0, phoneBook.getHumanWithPhone("1120"));
    }

    @Test
    public void getHumanWithPhoneTest1() {
        assertEquals(human2, phoneBook.getHumanWithPhone("3120"));
    }

    @Test
    public void getPhoneBookStartWithTest0() {
        assertEquals(result, phoneBook.getPhoneBookStartWith("Bl"));
    }
}
