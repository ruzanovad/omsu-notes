package com.ruska112.lab3.filter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContainsStringFilterTest {

    ContainsStringFilter containsStringFilter;

    @Test
    public void applyGoodTest() {
        containsStringFilter = new ContainsStringFilter("tT");
        assertTrue(containsStringFilter.apply("ProductTitle"));
    }

    @Test
    public void applyBadTest() {
        containsStringFilter = new ContainsStringFilter("e p");
        assertFalse(containsStringFilter.apply("Title Product"));
    }
}