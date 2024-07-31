package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.ruska112.CollectionsDemo.getNamesakesArrayList;
import static org.junit.Assert.assertArrayEquals;

public class GetNamesakesArrayListTest {

    // exercise 2
    ArrayList<Human> humans;
    ArrayList<Human> namesakes0;
    ArrayList<Human> namesakes1;
    Human namesake0;
    Human namesake1;

    @Before
    public void setUp() throws Exception {
        // exercise 2
        humans = new ArrayList<>();
        humans.add(new Human("Adolf", "Miron", "Vladimirovich", 70));
        humans.add(new Human("Mikyla", "Akyla", "Sharkovich", 5));
        humans.add(new Human("Lazy", "Ded", "Starikovich", 99999));
        humans.add(new Human("Mikyla", "GerHard", "Svyatoslavovich", 19));

        namesakes0 = new ArrayList<>();
        namesakes0.add(new Human("Mikyla", "Akyla", "Sharkovich", 5));
        namesakes0.add(new Human("Mikyla", "GerHard", "Svyatoslavovich", 19));

        namesake0 = new Human("Mikyla", "GerHard", "Svyatoslavovich", 19);

        namesakes1 = new ArrayList<>();
        namesakes1.add(new Human("Lazy", "Ded", "Starikovich", 99999));
        namesake1 = new Human("Lazy", "Bob", "Alice", 32);
    }

    @Test
    public void getArrayListNamesakesTest0() {
        assertArrayEquals(namesakes0.toArray(), getNamesakesArrayList(humans, namesake0).toArray());
    }

    @Test
    public void getArrayListNamesakesTest1() {
        assertArrayEquals(namesakes1.toArray(), getNamesakesArrayList(humans, namesake1).toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getArrayListNamesakesTest2() {
        ArrayList<Human> emptyHumans = null;
        getNamesakesArrayList(null, new Human("1", "1", "1", 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getArrayListNamesakesTest3() {
        Human emptyHuman = null;
        getNamesakesArrayList(humans, emptyHuman);
    }

}
