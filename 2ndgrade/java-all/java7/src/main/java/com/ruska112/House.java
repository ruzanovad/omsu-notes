package com.ruska112;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class House implements Serializable {
    private String cNumber;
    private String address;
    private Person mainPerson;

    private List<Flat> flatList;

    @JsonCreator
    public House(@JsonProperty(value = "cNumber") String cNumber,
                 @JsonProperty(value = "address") String address,
                 @JsonProperty(value = "mainPerson") Person mainPerson,
                 @JsonProperty(value = "flatList") List<Flat> flatList) {
        setcNumber(cNumber);
        setAddress(address);
        setMainPerson(mainPerson);
        setFlatList(flatList);
    }

    public House(String cNumber, String address, Person mainPerson, Flat... flats) {
        setcNumber(cNumber);
        setAddress(address);
        setMainPerson(mainPerson);
        setFlatList(Arrays.stream(flats).toList());
    }

    public String getcNumber() {
        return cNumber;
    }


    public void setcNumber(String cNumber) {
        if (cNumber == null) {
            throw new IllegalArgumentException();
        }
        this.cNumber = cNumber;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException();
        }
        this.address = address;
    }


    public Person getMainPerson() {
        return mainPerson;
    }


    public void setMainPerson(Person mainPerson) {
        if (mainPerson == null) {
            throw new IllegalArgumentException();
        }
        this.mainPerson = mainPerson;
    }


    public List<Flat> getFlatList() {
        return flatList;
    }


    public void setFlatList(List<Flat> flatList) {
        if (flatList == null) {
            throw new IllegalArgumentException();
        }
        this.flatList = flatList;
    }

    public static String getJSONStringFromHouse(House house) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(house);
    }

    public static House getHouseFromJSONString(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, House.class);
    }

    public static void saveHouseToCSV(House house) {
        try (var fileOS = new PrintWriter(new FileOutputStream(String.format("house_%s.csv", house.getcNumber())))) {
            fileOS.print(String.format(";Данные о доме:\n;\nКадастровый номер:;%s;\nАдрес:;%s;\nСтарший по дому:;%s %s\n;\n",
                    house.getcNumber(), house.getAddress(),
                    house.getMainPerson().getLastName(), house.getMainPerson().getFirstName()));
            fileOS.print("№;Площадь кв.м;Владельцы\n");
            for (Flat flat : house.getFlatList()) {
                fileOS.print(String.format("%d;%f;", flat.getRoom(), flat.getSquare()));
                for (Person person : flat.getOwners()) {
                    fileOS.print(String.format("%s %c.", person.getLastName(), person.getFirstName().charAt(0)));
                }
                fileOS.print(";\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(cNumber, house.cNumber) &&
                Objects.equals(address, house.address) &&
                Objects.equals(mainPerson, house.mainPerson) &&
                Objects.equals(flatList, house.flatList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cNumber, address, mainPerson, flatList);
    }
}
