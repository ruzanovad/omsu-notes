package com.ruska112.sem3;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StringServiceTest {

    @Test
    public void getCountOfStringsStartsWith() {
        String[] strArr = new String[]{"1", "2", "3"};
        assertEquals(1, StringService.getCountOfStringsStartsWith(strArr, '1'));
    }

    @Test
    public void getCountOfStringEquals() {
        String[] strArr = new String[]{"1", "12", "123"};
        assertEquals(0, StringService.getCountOfStringsEquals(strArr, "2"));
    }

    @Test
    public void getCountOfStringsWithoutSubstring() {
        try {
            String[] atrArr = new String[]{"b", "aa", "aaa"};
            assertEquals(1, StringService.getCountOfStringsWithoutSubstring(atrArr, "a"));
        } catch (IllegalArgumentException exception) {
            System.out.println("Error in getCountOfStringWithoutSubstring: " + exception.getMessage());
        }
    }

    @Test
    public void addNumAtStartTest() {
        String[] arr = new String[3];
        arr[0] = "f";
        arr[1] = "";
        arr[2] = null;
        assertArrayEquals(new String[] {"1 f", "2 ", "3 "}, StringService.addNumAtStart(arr));
    }
}