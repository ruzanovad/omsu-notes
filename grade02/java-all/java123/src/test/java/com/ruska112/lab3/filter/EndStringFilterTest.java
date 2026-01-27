package com.ruska112.lab3.filter;

import org.junit.Test;

import static org.junit.Assert.*;

public class EndStringFilterTest {

    EndStringFilter endStringFilter;

    @Test
    public void applyGoodTest() {
        endStringFilter = new EndStringFilter("1");
        assertTrue(endStringFilter.apply("Product1"));
    }

    @Test
    public void applyBadTest() {
        endStringFilter = new EndStringFilter("-1");
        assertFalse(endStringFilter.apply("Product1"));
    }
}