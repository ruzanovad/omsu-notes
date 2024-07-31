package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuadraticEquationTest {

    @Test
    public void QESolveTest0() throws NoRootsException {
        QuadraticEquation qe = new QuadraticEquation(1, 1, -6);
        Double[] res = {2.0, -3.0};
        assertArrayEquals(res, qe.solve().toArray());
    }

    @Test
    public void QESolveTest1() throws NoRootsException {
        QuadraticEquation qe = new QuadraticEquation(1, 2, 0);
        Double[] res = {0.0, -2.0};
        assertArrayEquals(res, qe.solve().toArray());
    }

    @Test
    public void QESolveTest2() throws NoRootsException {
        QuadraticEquation qe = new QuadraticEquation(1, 4, 4);
        Double[] res = {-2.0};
        assertArrayEquals(res, qe.solve().toArray());
    }

    @Test
    public void QESolveTest3() throws NoRootsException {
        QuadraticEquation qe = new QuadraticEquation(0, 1, -6);
        Double[] res = {6.0};
        assertArrayEquals(res, qe.solve().toArray());
    }

    @Test(expected = NoRootsException.class)
    public void QESolveTest4() throws NoRootsException {
        QuadraticEquation qe = new QuadraticEquation(1, 0, 6);
        Double[] res = {};
        qe.solve();
    }

    @Test(expected = NoRootsException.class)
    public void QESolveTest5() throws NoRootsException {
        QuadraticEquation qe = new QuadraticEquation(0, 0, 6);
        Double[] res = {};
        qe.solve();
    }
}