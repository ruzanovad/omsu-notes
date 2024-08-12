package com.ruska112.lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class FractionalLinearFunctionTest {

    @Test
    public void solveTest0() {
        FractionalLinearFunction fractionalLinearFunction = new FractionalLinearFunction(1, 1, 1, 1, -100, 100);
        assertEquals(1.0, fractionalLinearFunction.solve(1), 0.001);
    }

    @Test
    public void solveTest1() {
        FractionalLinearFunction fractionalLinearFunction = new FractionalLinearFunction(1, 2, 3, 4, -100, 100);
        assertEquals(0.4286, fractionalLinearFunction.solve(1), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void solveTest2() {
        FractionalLinearFunction fractionalLinearFunction = new FractionalLinearFunction(1, 1, 1, 1, -100, 100);
        fractionalLinearFunction.solve(200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void solveTest3() {
        FractionalLinearFunction fractionalLinearFunction = new FractionalLinearFunction(1, 1, 1, -1, -100, 100);
        fractionalLinearFunction.solve(1);
    }

}