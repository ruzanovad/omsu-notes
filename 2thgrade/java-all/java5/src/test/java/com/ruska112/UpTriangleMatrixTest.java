package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpTriangleMatrixTest {
    UpTriangleMatrix utm1 = new UpTriangleMatrix(3);

    @Before
    public void setup() {
        utm1.array = new double[]{1, 2, 3, 0, 3, 3, 0, 0, 9};
    }

    @Test
    public void setIJTest0() {
        utm1.setIJ(1, 2, 4);
        assertEquals(4, utm1.getIJ(1, 2), 0.001);
    }

    @Test
    public void setIJTest1() {
        utm1.setIJ(1, 1, 4);
        assertEquals(4, utm1.getIJ(1, 1), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIJTestBad() {
        utm1.setIJ(2, 0, 4);
    }

    @Test
    public void getDeterminantTest0() {
        assertEquals(27, utm1.getDeterminant(), 0.001);
    }

    @Test
    public void actualDeterminantTest0() {
        Matrix matrix = new UpTriangleMatrix(3);
        matrix.array = new double[]
                {2, 4, 6, 0, 3, 3, 0, 0, 9};
        matrix.getDeterminant(); // 54
        matrix.setIJ(1, 1, 5);
        assertEquals(90, matrix.getDeterminant(), 0.001);
    }

    @Test
    public void actualDeterminantTest1() {
        Matrix matrix = new UpTriangleMatrix(4);
        matrix.array = new double[]
                {2, 4, 6, 8,
                        0, 3, 3, 3,
                        0, 0, 6, 9,
                        0, 0, 0, 7};
        matrix.getDeterminant(); // 252
        matrix.setIJ(1, 1, 5);
        assertEquals(420, matrix.getDeterminant(), 0.001);
    }

    @Test
    public void getSizeTest() {
        assertEquals(3, utm1.getSize());
    }

    @Test
    public void equalsTest() {
        UpTriangleMatrix utm2 = new UpTriangleMatrix(utm1.getSize());
        for (int i = 0; i < utm1.getSize(); i++) {
            for (int j = 0; j < utm1.getSize(); j++) {
                if (j >= i) {
                    utm2.setIJ(i, j, utm1.getIJ(i, j));
                }
            }
        }
        assertTrue(utm1.equals(utm2));
    }
}