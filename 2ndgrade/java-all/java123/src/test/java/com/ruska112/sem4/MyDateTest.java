package com.ruska112.sem4;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyDateTest {

    @Test
    public void isBeforeTest() {
        MyDate date1 = new MyDate(2002, "march", 18);
        MyDate date2 = new MyDate(2003, "june", 8);
        assertTrue(date1.isBefore(date2));
    }

    @Test
    public void isBeforeTestSameYear() {
        MyDate date1 = new MyDate(2003, "march", 18);
        MyDate date2 = new MyDate(2003, "june", 8);
        assertTrue(date1.isBefore(date2));
    }

    @Test
    public void isBeforeTestSameYearSameMonth() {
        MyDate date1 = new MyDate(2003, "june", 8);
        MyDate date2 = new MyDate(2003, "june", 18);
        assertTrue(date1.isBefore(date2));
    }

    @Test
    public void isBeforeSameDate() {
        MyDate date1 = new MyDate(1970, "january", 1);
        MyDate date2 = new MyDate(1970, "january", 1);
        assertFalse(date1.isBefore(date2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestYearEL() {
        MyDate testDate = new MyDate(-1, "january", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestYearEM() {
        MyDate testDate = new MyDate(3020, "january", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestMonthE() {
        MyDate testDate = new MyDate(2020, "JabUary", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestMonthEIL() {
        MyDate testDate = new MyDate(2020, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestMonthEIM() {
        MyDate testDate = new MyDate(2020, 13, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestDayEL() {
        MyDate testDate = new MyDate(2020, "january", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTestDayEM() {
        MyDate testDate = new MyDate(2020, "january", 69);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMonthTestS() {
        MyDate testDate = new MyDate(2020, "january", 1);
        testDate.setMonth("123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMonthTestIM() {
        MyDate testDate = new MyDate(2020, "january", 1);
        testDate.setMonth(13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDayTestIL() {
        MyDate testDate = new MyDate(2020, "january", 1);
        testDate.setDay(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDayTestIM() {
        MyDate testDate = new MyDate(2020, "january", 1);
        testDate.setDay(69);
    }

    @Test
    public void setDay2020February29() {
        MyDate testDate = new MyDate(2020, 2, 29);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDay2021February29() {
        MyDate testDate = new MyDate(2021, 2, 29);
    }
}