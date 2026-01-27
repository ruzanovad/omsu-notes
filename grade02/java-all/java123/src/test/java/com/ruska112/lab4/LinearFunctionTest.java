package com.ruska112.lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinearFunctionTest {

    @Test
    public void solveTest0() {
        LinearFunction linearFunction = new LinearFunction(1, 1, -100, 100);
        assertEquals(2.0, linearFunction.solve(1), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void solveTest1() {
        LinearFunction linearFunction = new LinearFunction(1, 1, -100, 100);
        assertEquals(2.0, linearFunction.solve(112), 0.001);
    }

    @Test
    public void solveTest2() {
        LinearFunction linearFunction = new LinearFunction(2, -4, -100, 100);
        assertEquals(0.0, linearFunction.solve(2), 0.001);
    }
}