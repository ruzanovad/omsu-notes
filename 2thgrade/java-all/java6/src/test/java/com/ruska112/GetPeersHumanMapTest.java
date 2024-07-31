package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.ruska112.CollectionsDemo.getPeersHumanMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class GetPeersHumanMapTest {
    Set<Human> humanSet = new HashSet<>();

    ArrayList<Human> human0 = new ArrayList<>();
    ArrayList<Human> human1 = new ArrayList<>();
    ArrayList<Human> human2 = new ArrayList<>();

    Map<Integer, ArrayList<Human>> humanArrayListMap = new HashMap<>();

    @Before
    public void setUp() {
        humanSet.add(new Human("1", "1", "1", 1));
        humanSet.add(new Student("2", "2", "2", 2, "2"));
        humanSet.add(new Human("3", "3", "3", 3));
        humanSet.add(new Student("1", "1", "1", 1, "1"));
        humanSet.add(new Student("22", "22", "22", 2, "22"));
        humanSet.add(new Human("11", "11", "11", 1));

        human0.add(new Human("1", "1", "1", 1));
        human0.add(new Student("1", "1", "1", 1, "1"));
        human0.add(new Human("11", "11", "11", 1));

        human1.add(new Student("2", "2", "2", 2, "2"));
        human1.add(new Student("22", "22", "22", 2, "22"));

        human2.add(new Human("3", "3", "3", 3));

        humanArrayListMap.put(1, human0);
        humanArrayListMap.put(2, human1);
        humanArrayListMap.put(3, human2);
    }

    @Test
    public void getPeersHumanMapTest0() {
        assertEquals(humanArrayListMap, getPeersHumanMap(humanSet));
    }
}
