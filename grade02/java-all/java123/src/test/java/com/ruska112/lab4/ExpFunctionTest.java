package com.ruska112.lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpFunctionTest {

    @Test
    public void solveTest0() {
        ExpFunction expFunction = new ExpFunction(1, 0, -100, 100);
        assertEquals(1.0, expFunction.solve(0), 0.001);
    }

    @Test
    public void solveTest1() {
        ExpFunction expFunction = new ExpFunction(1, 0, -100, 100);
        assertEquals(15.1543, expFunction.solve(Math.E), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void solveTest2() {
        ExpFunction expFunction = new ExpFunction(1, 0, -100, 100);
        expFunction.solve(200);
    }
}