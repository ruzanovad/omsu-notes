package com.ruska112;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.ruska112.MatrixService.arrangeMatrices;

public class MatrixServiceTest {

    MatrixComparator matrixComparator = new MatrixComparator();

    Matrix[] array0;

    Matrix[] result0;

    Matrix[] array1;

    Matrix[] result1;
    Matrix m1 = new Matrix(3);

    DiagMatrix dm1 = new DiagMatrix(4);

    UpTriangleMatrix utm1 = new UpTriangleMatrix(2);

    Matrix m2 = new Matrix(4);

    @Before
    public void setup() {
        m1.array = new double[]
                {2, 4, 6,
                3, 3, 3,
                4, 5, 9}; // -18
        dm1.array = new double[]
                {2, 0, 0, 0,
                0, 4, 0, 0,
                0, 0, 6, 0,
                0, 0, 0, 8}; // 384
        utm1.array = new double[]{1, 2, 0, 5}; // 5
        m2.array = new double[]
                {1, 4, 8, 8,
                0, 2, 2, 8,
                1, 3, 3, 7,
                9, 8, 5, 2}; // -102

        array0 = new Matrix[]{dm1, utm1, m1};
        result0 = new Matrix[]{m1, utm1, dm1};

        array1 = new Matrix[]{dm1, utm1, m1, m2};
        result1 = new Matrix[]{m2, m1, utm1, dm1};
    }

    @Test
    public void comparatorTest0() {
        arrangeMatrices(array0, matrixComparator);
        assertArrayEquals(result0, array0);
    }

    @Test
    public void comparatorTest1() {
        arrangeMatrices(array1, matrixComparator);
        assertArrayEquals(result1, array1);
    }
}