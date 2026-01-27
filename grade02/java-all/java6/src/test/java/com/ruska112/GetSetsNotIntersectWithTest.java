package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.ruska112.CollectionsDemo.getSetsNotIntersectWith;
import static org.junit.Assert.assertArrayEquals;

public class GetSetsNotIntersectWithTest {
    // exercise 4

    Set<Integer> set;
    Set<Integer> set0;

    Set<Integer> setEmpty;
    Set<Integer> set1;
    Set<Integer> set2;

    ArrayList<Set<Integer>> setArrayList;
    ArrayList<Set<Integer>> setArrayListWithoutSet;

    @Before
    public void setUp() throws Exception {
        // exercise 4
        set = new HashSet<>();
        set.add(0);
        set.add(1);

        set0 = new HashSet<>();
        set0.add(3);
        set0.add(4);
        set0.add(5);

        setEmpty = new HashSet<>();

        set1 = new HashSet<>();
        set1.add(-1);
        set1.add(4);
        set1.add(9);

        set2 = new HashSet<>();
        set2.add(0);
        set2.add(999);

        setArrayList = new ArrayList<>();
        setArrayList.add(set);
        setArrayList.add(set0);
        setArrayList.add(setEmpty);
        setArrayList.add(set1);
        setArrayList.add(set2);

        setArrayListWithoutSet = new ArrayList<>();
        setArrayListWithoutSet.add(set0);
        setArrayListWithoutSet.add(setEmpty);
        setArrayListWithoutSet.add(set1);
    }

    @Test
    public void getSetsNotIntersectWithTest0() {
        assertArrayEquals(setArrayListWithoutSet.toArray(), getSetsNotIntersectWith(setArrayList, set).toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSetsNotIntersectWithTest1() {
        getSetsNotIntersectWith(null, set);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSetsNotIntersectWithTest2() {
        getSetsNotIntersectWith(setArrayList, null);
    }

}
