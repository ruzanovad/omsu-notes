package com.ruska112;

import java.io.*;

public class MySerializer {
    public static void serializePerson(Person person, String filename) throws IOException {
        try (ObjectOutput out = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(filename)))) {
            out.writeObject(person);
        }
    }

    public static void serializeFlat(Flat flat, String filename) throws IOException {
        try (ObjectOutput out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(filename)))) {
            out.writeObject(flat);
        }
    }

    public static void serializeHouse(House house, String filename) throws IOException {
        try (ObjectOutput out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(filename)))) {
            out.writeObject(house);
        }
    }
}
