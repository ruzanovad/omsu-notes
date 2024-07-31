package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.ruska112.CollectionsDemo.getAgeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetAgeMapTest {
    // exercise 9
    Map<Integer, Human> humanMap = new HashMap<>();

    Map<Integer, Integer> integerMap = new HashMap<>();

    @Before
    public void setUp() {
        humanMap.put(0, new Human("0", "0", "0", 0));
        humanMap.put(1, new Student("1", "1", "1", 19, "1"));
        humanMap.put(2, new Human("2", "2", "2", 2));
        humanMap.put(3, new Human("3", "3", "3", 30));
        humanMap.put(4, new Student("4", "4", "4", 4, "4"));
        humanMap.put(5, new Student("5", "5", "5", 18, "5"));

        integerMap.put(0, 0);
        integerMap.put(1, 19);
        integerMap.put(2, 2);
        integerMap.put(3, 30);
        integerMap.put(4, 4);
        integerMap.put(5, 18);
    }

    @Test
    public void getAgeMapTest0() {
        assertEquals(integerMap, getAgeMap(humanMap));
    }
}
