package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.ruska112.CollectionsDemo.getHumanSet;
import static org.junit.Assert.*;

public class GetHumanSetTest {
    // exercise 7
    Map<Integer, Human> humanMap = new HashMap<>();
    Set<Human> resultSet0 = new HashSet<>();

    Set<Human> resultSet1 = new HashSet<>();
    Set<Integer> integerSet0 = new HashSet<>();

    Set<Integer> integerSet1 = new HashSet<>();

    @Before
    public void setUp() {
        humanMap.put(0, new Human("0", "0", "0", 0));
        humanMap.put(1, new Student("1", "1", "1", 1, "1"));
        humanMap.put(2, new Human("2", "2", "2", 2));
        humanMap.put(3, new Human("3", "3", "3", 3));
        humanMap.put(4, new Student("4", "4", "4", 4, "4"));

        resultSet0.add(new Human("0", "0", "0", 0));
        resultSet0.add(new Student("4", "4", "4", 4, "4"));

        integerSet0.add(0);
        integerSet0.add(4);

        resultSet1.add(new Student("1", "1", "1", 1, "1"));
        resultSet1.add(new Human("3", "3", "3", 3));

        integerSet1.add(1);
        integerSet1.add(3);
    }

    @Test
    public void getHumanSetTest0() {
        assertEquals(resultSet0, getHumanSet(humanMap, integerSet0));
    }

    @Test
    public void getHumanSetTest1() {
        assertEquals(resultSet1, getHumanSet(humanMap, integerSet1));
    }
}

