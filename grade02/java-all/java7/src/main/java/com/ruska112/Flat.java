package com.ruska112;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flat implements Serializable {
    private int room;
    private double square;
    private List<Person> owners;

    @JsonCreator
    public Flat(@JsonProperty(value = "room") int room,@JsonProperty(value = "square")  double square,@JsonProperty(value = "owners")  List<Person> owners) {
        setRoom(room);
        setSquare(square);
        setOwners(new ArrayList<>(owners));
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        if (room <= 0) {
            throw new IllegalArgumentException();
        }
        this.room = room;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        if (square <= 0) {
            throw new IllegalArgumentException();
        }
        this.square = square;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        if (owners == null) {
            throw new IllegalArgumentException();
        }
        this.owners = owners;
    }

    public static String getJSONStringFromFlat(Flat flat) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(flat);
    }

    public static Flat getFlatFromJSONString(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Flat.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return room == flat.room && Math.abs(flat.square - square) <= 0.1 && Objects.equals(owners, flat.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, square, owners);
    }
}
