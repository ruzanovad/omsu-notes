package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.ruska112.CollectionsDemo.deleteHumanInArrayList;
import static org.junit.Assert.assertArrayEquals;

public class DeleteHumanInArrayListTest {
    ArrayList<Human> humans;
    // exercise 3
    Human adolf;
    ArrayList<Human> withoutAdolf;

    ArrayList<Human> humans1;
    Human tmp;
    ArrayList<Human> withoutTmp;

    @Before
    public void setUp() throws Exception {
        humans = new ArrayList<>();
        humans.add(new Human("Adolf", "Miron", "Vladimirovich", 70));
        humans.add(new Human("Mikyla", "Akyla", "Sharkovich", 5));
        humans.add(new Human("Lazy", "Ded", "Starikovich", 99999));
        humans.add(new Human("Mikyla", "GerHard", "Svyatoslavovich", 19));

        // exercise 3
        adolf = new Human("Adolf", "Miron", "Vladimirovich", 70);
        withoutAdolf = new ArrayList<>();
        withoutAdolf.add(new Human("Mikyla", "Akyla", "Sharkovich", 5));
        withoutAdolf.add(new Human("Lazy", "Ded", "Starikovich", 99999));
        withoutAdolf.add(new Human("Mikyla", "GerHard", "Svyatoslavovich", 19));

        humans1 = new ArrayList<>();
        humans1.add(new Human("1", "1", "1", 1));
        humans1.add(new Human("2", "2", "2", 2));
        humans1.add(new Human("3", "3", "3", 3));
        humans1.add(new Human("1", "1", "1", 1));
        humans1.add(new Human("5", "5", "5", 5));

        tmp = new Human("1", "1", "1", 1);

        withoutTmp = new ArrayList<>();
        withoutTmp.add(new Human("2", "2", "2", 2));
        withoutTmp.add(new Human("3", "3", "3", 3));
        withoutTmp.add(new Human("5", "5", "5", 5));
    }

    @Test
    public void deleteHumanInArrayListTest0() {
        assertArrayEquals(withoutAdolf.toArray(), deleteHumanInArrayList(humans, adolf).toArray());
    }

    @Test
    public void deleteHumanInArrayListTest1() {
        assertArrayEquals(withoutTmp.toArray(), deleteHumanInArrayList(humans1, tmp).toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteHumanInArrayListTest2() {
        deleteHumanInArrayList(null, tmp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteHumanInArrayListTest3() {
        deleteHumanInArrayList(humans1, null);
    }

}
