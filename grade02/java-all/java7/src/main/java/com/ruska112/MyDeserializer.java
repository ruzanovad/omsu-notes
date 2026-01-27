package com.ruska112;

import java.io.*;

public class MyDeserializer {
    public static Person deserializePerson(String filename) throws IOException, ClassNotFoundException, ClassCastException {
        try (ObjectInput in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(filename)))) {
            return (Person) in.readObject();
        }
    }

    public static Flat deserializeFlat(String filename) throws IOException, ClassNotFoundException, ClassCastException {
        try (ObjectInput in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(filename)))) {
            return (Flat) in.readObject();
        }
    }

    public static House deserializeHouse(String filename) throws IOException, ClassNotFoundException, ClassCastException {
        try (ObjectInput in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(filename)))) {
            return (House) in.readObject();
        }
    }
}
