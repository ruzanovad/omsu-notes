package com.ruska112;

import org.junit.Test;

import java.util.*;

import static com.ruska112.StreamApiDemo.*;
import static com.ruska112.LambdaRunner.*;
import static org.junit.Assert.*;

public class StreamApiDemoTest {
    @Test
    public void deleteAllNullTest0() {
        var list = new ArrayList<>();
        var list1 = new ArrayList<>();

        var obj0 = new Object();
        var obj1 = new Object();
        var obj2 = new Object();

        list.add(null);
        list.add(obj0);
        list.add(obj1);
        list.add(null);
        list.add(obj2);

        list1.add(obj0);
        list1.add(obj1);
        list1.add(obj2);
        assertEquals(list1, deleteAllNull.apply(list));
    }

    @Test
    public void getCountPositiveTest0() {
        var set = new HashSet<Integer>();
        Collections.addAll(set, -2, -1, 0, 1, 2);
        assertEquals(Optional.of(2), Optional.of(getCountPositive.apply(set)));
    }

    @Test
    public void getLastThree() {
        var list = new ArrayList<>();
        var list1 = new ArrayList<>();

        var obj = new Object();
        var arr = new ArrayList<>();
        var set = new HashSet<>();
        Collections.addAll(list, null, new HashSet<>(), obj, arr, set);
        Collections.addAll(list1, obj, arr, set);
        assertEquals(list1, getLastThree.apply(list));
    }

    @Test
    public void getFirstEvenTest0() {
        var list = new ArrayList<Integer>();
        Collections.addAll(list, 1, 3, 5, 8);
        assertEquals(Optional.of(8), Optional.of(getFirstEven.apply(list)));
    }

    @Test
    public void getFirstEvenTest1() {
        var list = new ArrayList<Integer>();
        assertEquals(null, getFirstEven.apply(list));
    }

    @Test
    public void getSquaresTest0() {
        var list = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();
        Collections.addAll(list, 1, 2, 3, null, -2, -9, 0);
        Collections.addAll(list2, 1, 4, 9, 81, 0);
        assertEquals(list2, getSquares.apply(list));
    }

    @Test
    public void getSortAllNonEmptyTest0() {
        var list = new ArrayList<String>();
        var list1 = new ArrayList<String>();
        Collections.addAll(list, "", "def", "abc", null, "", "ghij");
        Collections.addAll(list1, "abc", "def", "ghij");
        assertEquals(list1, getSortAllNonEmpty.apply(list));
    }

    @Test
    public void getStringListTest0() {
        var list = new ArrayList<String>();
        var set = new HashSet<String>();
        Collections.addAll(list, "ghi", "def", "abc");
        Collections.addAll(set, "ghi", "abc", "def");
        assertEquals(list, getStringList.apply(set));
    }

    @Test
    public void getSumOfSquaresTest0() {
        var set = new HashSet<Integer>();
        Collections.addAll(set, 2, 3, 4, null, -3);
        assertEquals(Optional.of(38), Optional.of(getSumOfSquares.apply(set)));
    }

    @Test
    public void getHumanMaxAgeTest0() {
        var list = new ArrayList<Human>();
        Collections.addAll(list,
                new Human("1", "1", "1", 19, Gender.Male),
                new Human("2", "2", "2", 20, Gender.Male),
                new Human("3", "3", "3", 54, Gender.Male));

        assertEquals(Optional.of(54), Optional.of(getHumanMaxAge.apply(list)));
    }

    @Test
    public void getSortHumansTest0() {
        var list = new ArrayList<Human>();
        Collections.addAll(list,
                new Human("1", "1", "1", 19, Gender.Female),
                new Human("2", "2", "2", 20, Gender.Male),
                new Human("3", "3", "3", 54, Gender.Female));

        var list1 = new ArrayList<Human>();
        Collections.addAll(list1,
                new Human("2", "2", "2", 20, Gender.Male),
                new Human("1", "1", "1", 19, Gender.Female),
                new Human("3", "3", "3", 54, Gender.Female));
        assertEquals(list1, getSortHumans.apply(list));
    }

}
