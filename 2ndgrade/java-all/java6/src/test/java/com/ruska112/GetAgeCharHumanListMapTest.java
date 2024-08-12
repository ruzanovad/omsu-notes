package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.ruska112.CollectionsDemo.getAgeCharHumanListMap;
import static org.junit.Assert.*;

public class GetAgeCharHumanListMapTest {
    Set<Human> humanSet = new HashSet<>();

    ArrayList<Human> human0 = new ArrayList<>();
    ArrayList<Human> human1 = new ArrayList<>();
    ArrayList<Human> human2 = new ArrayList<>();

    Map<Character, ArrayList<Human>> humanArrayListMap0 = new HashMap<>();
    Map<Character, ArrayList<Human>> humanArrayListMap1 = new HashMap<>();
    Map<Character, ArrayList<Human>> humanArrayListMap2 = new HashMap<>();

    Map<Integer, Map<Character, ArrayList<Human>>> integerCharacterHumanListMap = new HashMap<>();


    @Test
    public void getAgeCharHumanListMapTest0() {
        humanSet.add(new Human("A", "1", "1", 1));
        humanSet.add(new Student("B", "2", "2", 2, "2"));
        humanSet.add(new Human("C", "3", "3", 3));
        humanSet.add(new Student("Aa", "1", "1", 1, "1"));
        humanSet.add(new Student("Bb", "22", "22", 2, "22"));
        humanSet.add(new Human("Aaa", "11", "11", 1));

        human0.add(new Human("A", "1", "1", 1));
        human0.add(new Student("Aa", "1", "1", 1, "1"));
        human0.add(new Human("Aaa", "11", "11", 1));

        human1.add(new Student("B", "2", "2", 2, "2"));
        human1.add(new Student("Bb", "22", "22", 2, "22"));

        human2.add(new Human("C", "3", "3", 3));

        humanArrayListMap0.put('A', human0);
        humanArrayListMap1.put('B', human1);
        humanArrayListMap2.put('C', human2);

        integerCharacterHumanListMap.put(3, humanArrayListMap2);
        integerCharacterHumanListMap.put(2, humanArrayListMap1);
        integerCharacterHumanListMap.put(1, humanArrayListMap0);
        assertEquals(integerCharacterHumanListMap, getAgeCharHumanListMap(humanSet));
    }

    @Test
    public void getAgeCharHumanListMapTest1() {
        humanSet.add(new Human("A", "1", "1", 1));
        humanSet.add(new Student("A", "2", "2", 2, "2"));
        humanSet.add(new Human("A", "3", "3", 3));
        humanSet.add(new Student("Aa", "1", "1", 1, "1"));
        humanSet.add(new Student("Ab", "22", "22", 2, "22"));
        humanSet.add(new Human("Aaa", "11", "11", 1));

        human0.add(new Human("A", "1", "1", 1));
        human0.add(new Student("Aa", "1", "1", 1, "1"));
        human0.add(new Human("Aaa", "11", "11", 1));

        human1.add(new Student("A", "2", "2", 2, "2"));
        human1.add(new Student("Ab", "22", "22", 2, "22"));

        human2.add(new Human("A", "3", "3", 3));

        humanArrayListMap0.put('A', human0);
        humanArrayListMap1.put('A', human1);
        humanArrayListMap2.put('A', human2);

        integerCharacterHumanListMap.put(3, humanArrayListMap2);
        integerCharacterHumanListMap.put(2, humanArrayListMap1);
        integerCharacterHumanListMap.put(1, humanArrayListMap0);
        assertEquals(integerCharacterHumanListMap, getAgeCharHumanListMap(humanSet));
    }
}
