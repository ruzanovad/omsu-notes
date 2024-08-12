package com.ruska112.sem4;

import org.junit.Test;

import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class HumanServiceTest {
    @Test
    public void getAllAdultsTest() {
        ArrayList<Human> people = new ArrayList<>();
        Human ded = new Human("Ded", new MyDate(2002, 3, 18));
        Human hard = new Human("Hard", new MyDate(2000, 5, 14));
        people.add(ded);
        people.add(hard);
        people.add(new Human("Dedus", new MyDate(2013, 8, 11)));
        people.add(new Human("this", new MyDate(2004, YearMonth.now().getMonthValue(), MonthDay.now().getDayOfMonth())));

        ArrayList<Human> result = new ArrayList<>();
        result.add(ded);
        result.add(hard);

        ArrayList<Human> adults = HumanService.getAllAdults(people);

        assertArrayEquals(result.toArray(), adults.toArray());
    }

    @Test
    public void getAgesTest() {
        ArrayList<Human> people = new ArrayList<>();
        people.add(new Human("Ded", new MyDate(2002, 3, 18)));
        people.add(new Human("Hard", new MyDate(2000, 5, 14)));
        people.add(new Human("Dedus", new MyDate(2013, 8, 11)));

        ArrayList<Integer> ages = HumanService.getAges(people);

        Integer[] agesInt = new Integer[]{20, 22, 9};

        assertArrayEquals(agesInt, ages.toArray());
    }
}