package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolveUserTest {
    @Test
    public void getLargerTest0() throws NoRootsException {
        SolveUser su = new SolveUser(new QuadraticEquation(1, 1, -6));
        assertEquals(2.0, su.getLarger(), 0.1);
    }

    @Test
    public void getLargerTest1() throws NoRootsException {
        SolveUser su = new SolveUser(new QuadraticEquation(1, 4, 4));
        assertEquals(-2.0, su.getLarger(), 0.1);
    }

    @Test
    public void getLargerTest2() throws NoRootsException {
        SolveUser su = new SolveUser(new QuadraticEquation(1, 2, 0));
        assertEquals(0.0, su.getLarger(), 0.1);
    }

    @Test(expected = NoRootsException.class)
    public void getLargerTest4() throws NoRootsException {
        SolveUser su = new SolveUser(new QuadraticEquation(0, 0, 0));
        su.getLarger();
    }
}