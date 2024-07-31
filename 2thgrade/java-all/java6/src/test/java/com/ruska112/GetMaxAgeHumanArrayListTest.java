package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.ruska112.CollectionsDemo.getMaxAgeHumanArrayList;
import static org.junit.Assert.assertArrayEquals;

public class GetMaxAgeHumanArrayListTest {
    // exercise 5

    Human human0;
    Student human1;
    Human human2;
    Student human3;

    ArrayList<Human> humans2;

    ArrayList<Human> humans2maxAge;

    @Before
    public void setUp() throws Exception {

        // exercise 5
        human0 = new Human("0", "0", "0", 77);
        human1 = new Student("1", "1", "1", 19, "1");
        human2 = new Human("2", "2", "2", 20);
        human3 = new Student("3", "3", "3", 77, "3");

        humans2 = new ArrayList<>();
        humans2.add(human0);
        humans2.add(human1);
        humans2.add(human2);
        humans2.add(human3);

        humans2maxAge = new ArrayList<>();
        humans2maxAge.add(human0);
        humans2maxAge.add(human3);
    }

    @Test
    public void getMaxAgeHumanArrayListTest0() {
        assertArrayEquals(humans2maxAge.toArray(), getMaxAgeHumanArrayList(humans2).toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getMaxAgeHumanArrayListTest1() {
        getMaxAgeHumanArrayList(null);
    }
}
