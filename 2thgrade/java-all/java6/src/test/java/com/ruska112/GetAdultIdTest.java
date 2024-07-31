package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ruska112.CollectionsDemo.getAdultId;
import static org.junit.Assert.assertArrayEquals;

public class GetAdultIdTest {
    // exercise 8
    Map<Integer, Human> humanMap = new HashMap<>();

    ArrayList<Integer> integerArrayList = new ArrayList<>();

    @Before
    public void setUp() {
        humanMap.put(0, new Human("0", "0", "0", 0));
        humanMap.put(1, new Student("1", "1", "1", 19, "1"));
        humanMap.put(2, new Human("2", "2", "2", 2));
        humanMap.put(3, new Human("3", "3", "3", 30));
        humanMap.put(4, new Student("4", "4", "4", 4, "4"));
        humanMap.put(5, new Student("5", "5", "5", 18, "5"));

        integerArrayList.add(1);
        integerArrayList.add(3);
        integerArrayList.add(5);
    }

    @Test
    public void getAdultIdTest0() {
        assertArrayEquals(integerArrayList.toArray(), getAdultId(humanMap).toArray());
    }
}
