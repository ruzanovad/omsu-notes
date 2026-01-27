package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.ruska112.CollectionsDemo.getSortedArrayList;
import static org.junit.Assert.assertArrayEquals;

public class GetSortedArrayListTest {
    // exercise 6

    Human[] humans3;
    ArrayList<Human> humans3Sorted;

    @Before
    public void setUp() throws Exception {
        // exercise 6
        humans3 = new Human[]{
                new Human("Zork", "1", "1", 1),
                new Student("Wolf", "2", "2", 2, "2"),
                new Human("Viol", "3", "3", 3),
                new Student("Bell", "4", "4", 4, "4"),
                new Student("Asus", "5", "5", 5, "5")
        };

        humans3Sorted = new ArrayList<>();
        humans3Sorted.add(new Student("Asus", "5", "5", 5, "5"));
        humans3Sorted.add(new Student("Bell", "4", "4", 4, "4"));
        humans3Sorted.add(new Human("Viol", "3", "3", 3));
        humans3Sorted.add(new Student("Wolf", "2", "2", 2, "2"));
        humans3Sorted.add(new Human("Zork", "1", "1", 1));
    }

    @Test
    public void getSortedArrayListTest0() {
        assertArrayEquals(humans3Sorted.toArray(), getSortedArrayList(humans3[0], humans3[1], humans3[2], humans3[3], humans3[4]).toArray());
    }
}
