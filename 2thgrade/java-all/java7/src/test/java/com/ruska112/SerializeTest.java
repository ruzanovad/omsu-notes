package com.ruska112;

import org.junit.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static com.ruska112.MySerializer.*;
import static com.ruska112.MyDeserializer.*;
import static com.ruska112.House.*;
public class SerializeTest {
    @Test
    public void serializePersonTest0() throws IOException, ClassNotFoundException {
        Person person = new Person("Mobi", "Dick", LocalDate.of(2000, 6, 15));
        serializePerson(person, "person0");
        assertEquals(person, deserializePerson("person0"));
    }

    @Test
    public void serializeFlatTest0() throws IOException, ClassNotFoundException {
        Person person0 = new Person("Mobi", "Dick", LocalDate.of(2000, 6, 15));
        Person person1 = new Person("Alan", "Lone", LocalDate.of(1987, 1, 2));
        ArrayList<Person> people = new ArrayList<>();
        Collections.addAll(people, person0, person1);
        Flat flat = new Flat(112, 89, people);
        serializeFlat(flat, "flat0");
        assertEquals(flat, deserializeFlat("flat0"));
    }

    @Test
    public void serializeHouseTest0() throws IOException, ClassNotFoundException {
        Person person0 = new Person("Mobi", "Dick", LocalDate.of(2000, 6, 15));
        Person person1 = new Person("Alan", "Lone", LocalDate.of(1987, 1, 2));
        ArrayList<Person> people0 = new ArrayList<>();
        Collections.addAll(people0, person0, person1);
        Flat flat0 = new Flat(1, 89, people0);
        Person person2 = new Person("Chebyrashka", "Borik", LocalDate.of(1980, 3, 14));
        ArrayList<Person> people1 = new ArrayList<>();
        Collections.addAll(people1, person2);
        Flat flat1 = new Flat(2, 64, people1);
        ArrayList<Flat> flats = new ArrayList<>();
        Collections.addAll(flats, flat0, flat1);
        House house = new House("0", "yl.Pushkina", person0, flats);
        serializeHouse(house, "house0");
        assertEquals(house, deserializeHouse("house0"));
    }


    @Test
    public void serializeHouseWithJacksonTest0() throws IOException {
        Person person0 = new Person("Mobi", "Dick", LocalDate.of(2000, 6, 15));
        Person person1 = new Person("Alan", "Lone", LocalDate.of(1987, 1, 2));
        ArrayList<Person> people0 = new ArrayList<>();
        Collections.addAll(people0, person0, person1);
        Flat flat0 = new Flat(1, 89, people0);
        Person person2 = new Person("Chebyrashka", "Borik", LocalDate.of(1980, 3, 14));
        ArrayList<Person> people1 = new ArrayList<>();
        Collections.addAll(people1, person2);
        Flat flat1 = new Flat(2, 64, people1);
        ArrayList<Flat> flats = new ArrayList<>();
        Collections.addAll(flats, flat0, flat1);
        House house = new House("0", "yl.Pushkina", person0, flats);
        String houseJSONString = "{\"cNumber\":\"0\",\"address\":\"yl.Pushkina\",\"mainPerson\":{\"firstName\":\"Mobi\",\"lastName\":\"Dick\",\"birthday\":[2000,6,15]},\"flatList\":[{\"room\":1,\"square\":89.0,\"owners\":[{\"firstName\":\"Mobi\",\"lastName\":\"Dick\",\"birthday\":[2000,6,15]},{\"firstName\":\"Alan\",\"lastName\":\"Lone\",\"birthday\":[1987,1,2]}]},{\"room\":2,\"square\":64.0,\"owners\":[{\"firstName\":\"Chebyrashka\",\"lastName\":\"Borik\",\"birthday\":[1980,3,14]}]}]}";
        assertEquals(houseJSONString, getJSONStringFromHouse(house));
    }

    @Test
    public void serializeHouseWithJacksonTest2() throws IOException {
        Person person0 = new Person("Mobi", "Dick", LocalDate.of(2000, 6, 15));
        Person person1 = new Person("Alan", "Lone", LocalDate.of(1987, 1, 2));
        ArrayList<Person> people0 = new ArrayList<>();
        Collections.addAll(people0, person0, person1);
        Flat flat0 = new Flat(1, 89, people0);
        Person person2 = new Person("Chebyrashka", "Borik", LocalDate.of(1980, 3, 14));
        ArrayList<Person> people1 = new ArrayList<>();
        Collections.addAll(people1, person2);
        Flat flat1 = new Flat(2, 64, people1);
        ArrayList<Flat> flats = new ArrayList<>();
        Collections.addAll(flats, flat0, flat1);
        House house = new House("0", "yl.Pushkina", person0, flats);
        String houseJSONString = "{\"cNumber\":\"0\",\"address\":\"yl.Pushkina\",\"mainPerson\":{\"firstName\":\"Mobi\",\"lastName\":\"Dick\",\"birthday\":[2000,6,15]},\"flatList\":[{\"room\":1,\"square\":89.0,\"owners\":[{\"firstName\":\"Mobi\",\"lastName\":\"Dick\",\"birthday\":[2000,6,15]},{\"firstName\":\"Alan\",\"lastName\":\"Lone\",\"birthday\":[1987,1,2]}]},{\"room\":2,\"square\":64.0,\"owners\":[{\"firstName\":\"Chebyrashka\",\"lastName\":\"Borik\",\"birthday\":[1980,3,14]}]}]}";
        assertEquals(house, getHouseFromJSONString(houseJSONString));
    }
}
