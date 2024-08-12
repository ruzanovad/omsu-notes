package com.ruska112;

import org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static com.ruska112.DataDemo.getAll;

public class DataTest {

    @Test
    public void groupIteratorTest0() {
        var group = new Group(0, 1, 2, 3, 4);
        int i = 0;
        int[] groupArr = new int[4];
        int[] result = new int[]{1, 2, 3, 4};
        for (Integer num : group) {
            groupArr[i] = num;
            i++;
        }
        assertArrayEquals(result, groupArr);
    }

    @Test
    public void dataIteratorTest0() {
        var data = new Data("data", new Group(0, 1, 2, 3), new Group(1), new Group(2),
                new Group(3, 4, 5, 6, 7, 8),
                new Group(4, 9, 10, 11, 12, 13, 14),
                new Group(5, 15, 16));
        int i = 0;
        int[] groupArr = new int[16];
        int[] result = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        for (Integer datum : data) {
            groupArr[i] = datum;
            i++;
        }
        assertArrayEquals(result, groupArr);
    }

    @Test
    public void getAllTest0() {
        var data = new Data("data", new Group(-2), new Group(-1), new Group(0, 0, 1, 2),
                new Group(1, 3, 4, 5, 6), new Group(2),
                new Group(3), new Group(4, 7, 8, 9), new Group(5), new Group(6));

        ArrayList<Integer> result = new ArrayList<>();
        Collections.addAll(result, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertArrayEquals(result.toArray(), getAll(data).toArray());
    }
}
