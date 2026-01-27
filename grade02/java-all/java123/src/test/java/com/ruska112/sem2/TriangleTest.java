package com.ruska112.sem2;

import com.ruska112.lab1.c3d.Point3D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TriangleTest {

    Triangle rightTriangle;
    Triangle isoscelesTriangle;

    @Before
    public void setup() {
        rightTriangle = new Triangle(new Point3D(0, 0, 0), new Point3D(3, 0, 0), new Point3D(0, 4, 0));
        isoscelesTriangle = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
    }

    @Test
    public void getPerimeterRightTriangleTest() {
        assertEquals(12, rightTriangle.getPerimeter(), 0.0001);
    }

    @Test
    public void getPerimeterIsoscelesTriangleTest() {
        assertEquals(4.24, isoscelesTriangle.getPerimeter(), 0.01);
    }

    @Test
    public void getAreaRightTriangleTest() {
        assertEquals(6, rightTriangle.getArea(), 0.0001);
    }

    @Test
    public void getAreaRightIsoscelesTest() {
        assertEquals(0.87, isoscelesTriangle.getArea(), 0.01);
    }

    @Test
    public void getMedianPointTest() {
        assertTrue(rightTriangle.getMedianPoint().equals(new Point3D(1, 1.33, 0), 0.01));
    }
}