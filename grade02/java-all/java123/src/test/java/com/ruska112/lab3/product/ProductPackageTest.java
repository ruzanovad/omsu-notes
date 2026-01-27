package com.ruska112.lab3.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductPackageTest {

    ProductPackage productPackage;

    @Before
    public void setup() {
        productPackage = new ProductPackage("ProductPackage", 100);
    }

    @Test
    public void setWeightGoodTest() {
        productPackage.setWeight(1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWeightBadTest() {
        productPackage.setWeight(0);
    }

    @Test
    public void getWeightTest() {
        assertEquals(100, productPackage.getWeight(), 0.1);
    }

    @Test
    public void testHashCodeTest() {
        Integer oneHundred = 100;
        assertEquals(oneHundred.hashCode() + "ProductPackage".hashCode(), productPackage.hashCode());
    }

    @Test
    public void testEqualsTest() {
        ProductPackage productPackage2 = productPackage;
        assertEquals(productPackage2, productPackage);
    }

    @Test
    public void testToStringTest() {
        assertEquals("Title: ProductPackage, Weight: 100.00", productPackage.toString());
    }
}