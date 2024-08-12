package com.ruska112.sem5;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialTest {

    Polynomial polynomial;


    @Test
    public void solveTest0() {
        polynomial = new Polynomial(1, 2, 3);
        assertEquals(3.0, polynomial.solve(0), 0.001);
    }

    @Test
    public void solveTest1() {
        polynomial = new Polynomial(1, 2, 3);
        assertEquals(11.0, polynomial.solve(2), 0.001);
    }

    @Test
    public void solveDerivativeTest0() {
        polynomial = new Polynomial(4, 3, 2, 1);
        assertEquals(2.0, polynomial.solveDerivative(0), 0.001);
    }

    @Test
    public void solveDerivativeTest1() {
        polynomial = new Polynomial(4, 3, 2, 1);
        assertEquals(20.0, polynomial.solveDerivative(1), 0.001);
    }
}