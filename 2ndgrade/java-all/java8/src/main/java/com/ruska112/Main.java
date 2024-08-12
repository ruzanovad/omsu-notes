package com.ruska112;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static com.ruska112.ReflectionDemo.*;
public class Main {
    public static void main(String[] args) {
        System.out.println(getListOfGettersAndSetters(new Human("1", "2", LocalDate.of(2000, 1, 2))));
    }
}