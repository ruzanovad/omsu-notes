package com.ruska112;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {
    Matrix m1 = new Matrix(3);
    Matrix m4 = new Matrix(3);
    Matrix m2 = new Matrix(3);

    Matrix m3 = new Matrix(4);

    Matrix m5 = new Matrix(4);

    Matrix zero = new Matrix(3);

    Matrix m6 = new Matrix(4);

    Matrix m250 = new Matrix(5);

    @Before
    public void setUp() throws Exception {
        m1.array = new double[]{2, 4, 6,
                3, 3, 3,
                4, 5, 9};
        m2.array = new double[]{-2, 4, 6,
                3, 3, 3,
                4, 5, -9};

        m3.array = new double[]{0, 2, 4, 6,
                0, 3, 8, 3,
                0, 4, 5, 9,
                1, 3, 4, 5};
        m4.array = new double[]{3, 3, 3,
                2, 4, 4,
                1, 3, 6};
        m5.array = new double[]
                {3, 1, 1, 1,
                        1, 3, 1, 1,
                        1, 1, 3, 1,
                        1, 1, 1, 3};

        zero.array = new double[]
                {0, 1, 2,
                        3, 0, 4,
                        5, 6, 0};

        m6.array = new double[]
                {-1, 3, -5, 5,
                        3, -3, 2, 7,
                        6, 7, 5, 8,
                        9, 2, -7, -2
                };

        m250.array = new double[]{
                3, -1, 0, -1, 0,
                0, 1, -2, 0, 2,
                0, -1, 0, 0, 4,
                3, 0, 6, -1, 3,
                5, 0, 3, 0, 1};

    }

    @Test
    public void getIJTest0() {
        Assert.assertEquals(2, m1.getIJ(0, 0), 0.001);
    }

    @Test
    public void getIJTest1() {
        Assert.assertEquals(3, m1.getIJ(1, 1), 0.001);
    }

    @Test
    public void getIJTest2() {
        Assert.assertEquals(6, m1.getIJ(0, 2), 0.001);
    }

    @Test
    public void setIJTest0() {
        m1.setIJ(0, 2, 999);
        Assert.assertEquals(999, m1.getIJ(0, 2), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setIJTestException() {
        m1.setIJ(0, 4, 999);
    }

    @Test
    public void getSizeTest() {
        Assert.assertEquals(3, m1.getSize());
    }

    @Test
    public void getDeterminantTest0() {
        Assert.assertEquals(-18, m1.getDeterminant(), 0.001);
    }

    @Test
    public void getDeterminantTest1() {
        Assert.assertEquals(258, m2.getDeterminant(), 0.001);
    }

    @Test
    public void getDeterminantTest2() {
        Assert.assertEquals(48, m3.getDeterminant(), 0.001);
    }

    @Test
    public void getDeterminantTest3() {
        Assert.assertEquals(18, m4.getDeterminant(), 0.001);
    }


    @Test
    public void getDeterminantTest4() {
        Assert.assertEquals(48, m5.getDeterminant(), 0.001);
    }

    @Test
    public void getDeterminantTest5() {
        Assert.assertEquals(56, zero.getDeterminant(), 0.001);
    }

    @Test
    public void getDeterminantTest6() {
        Assert.assertEquals(7595, m6.getDeterminant(), 0.001);
    }

    @Test
    public void getDeterminantTest7() {
        Assert.assertEquals(250, m250.getDeterminant(), 0.001);
    }

    @Test
    public void actualDeterminantTest0() {
        Matrix matrix = new Matrix(3);
        matrix.array = new double[]
                {2, 4, 6, 3, 3, 3, 4, 5, 9};
        matrix.getDeterminant(); // -18
        matrix.setIJ(0, 1, 5);
        assertEquals(-33, matrix.getDeterminant(), 0.001);
    }

    @Test
    public void actualDeterminantTest1() {
        Matrix matrix = new Matrix(4);
        matrix.array = new double[]
                {2, 4, 6, 7,
                        3, 3, 3, 4,
                        5, 9, 12, 14,
                        1, 2, 5, 7};
        matrix.getDeterminant(); // 5
        matrix.setIJ(0, 1, 5);
        assertEquals(-26, matrix.getDeterminant(), 0.001);
    }

    @Test
    public void equalsTest0() {
        assertNotEquals(m1, m2);
    }

    @Test
    public void equalsTest1() {
        Matrix m4 = new Matrix(m1.getSize());
        for (int i = 0; i < m1.getSize(); i++) {
            for (int j = 0; j < m1.getSize(); j++) {
                m4.setIJ(i, j, m1.getIJ(i, j));
            }
        }
        assertNotEquals(m1, m4);
    }
}