package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.ruska112.CollectionsDemo.getCountStringsStartingWith;
import static org.junit.Assert.*;

public class GetCountStringsStartingWithTest {
    // exercise 1
    ArrayList<String> strings;

    // exercise 2
    ArrayList<Human> humans;
    ArrayList<Human> namesakes0;
    ArrayList<Human> namesakes1;
    Human namesake0;
    Human namesake1;

    @Before
    public void setUp() throws Exception {
        // exercise 1
        strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbc");
        strings.add("");
        strings.add("aba");
        strings.add("bab");
    }

    @Test
    public void testGetCountStringsStartingWith0() {
        assertEquals(2, getCountStringsStartingWith(strings, 'a'));
    }

    @Test
    public void testGetCountStringsStartingWith1() {
        assertEquals(2, getCountStringsStartingWith(strings, 'b'));
    }

    @Test
    public void testGetCountStringsStartingWith2() {
        assertEquals(0, getCountStringsStartingWith(strings, 'c'));
    }

    @Test
    public void testGetCountStringsStartingWith3() {
        ArrayList<String> emptyStrings = new ArrayList<>();
        assertEquals(0, getCountStringsStartingWith(emptyStrings, 'c'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCountStringsStartingWith() {
        ArrayList<String> emptyArr = null;
        getCountStringsStartingWith(emptyArr, 'a');
    }
}
