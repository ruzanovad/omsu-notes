package com.ruska112.lab3.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PieceProductTest {

    PieceProduct pieceProduct;

    @Before
    public void setup() {
        pieceProduct = new PieceProduct();
    }

    @Test
    public void setWeightGoodTest() {
        pieceProduct.setWeight(1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWeightBadTest() {
        pieceProduct.setWeight(0);
    }

    @Test
    public void getWeightTest() {
        assertEquals(100, pieceProduct.getWeight(), 0.1);
    }

    @Test
    public void testHashCodeTest() {
        Integer oneHundred = 100;
        assertEquals(oneHundred.hashCode() + "PieceProduct".hashCode() + "Description".hashCode(), pieceProduct.hashCode());
    }

    @Test
    public void testEqualsTest() {
        PieceProduct pieceProduct2 = pieceProduct;
        assertEquals(pieceProduct2, pieceProduct);
    }

    @Test
    public void testToStringTest() {
        assertEquals("Title: PieceProduct\nDescription: Description\nWeight: 100.00\n", pieceProduct.toString());
    }
}