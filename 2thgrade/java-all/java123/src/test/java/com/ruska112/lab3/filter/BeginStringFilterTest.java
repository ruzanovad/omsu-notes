package com.ruska112.lab3.filter;

import org.junit.Test;

import static org.junit.Assert.*;

public class BeginStringFilterTest {

    BeginStringFilter beginStringFilter;

    @Test
    public void applyGoodTest() {
        beginStringFilter = new BeginStringFilter("Product");
        assertTrue(beginStringFilter.apply("ProductTitle"));
    }

    @Test
    public void applyBadTest() {
        beginStringFilter = new BeginStringFilter("Product");
        assertFalse(beginStringFilter.apply("Title Product"));
    }
}