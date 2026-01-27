package com.ruska112.lab3.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    Product product;

    @Before
    public void setup() {
        product = new Product("Product1", "Product1 Description");
    }

    @Test
    public void setTitleGoodTest() {
        product.setTitle("Product2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTitleBadTest0() {
        product.setTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTitleBadTest1() {
        product.setTitle("");
    }

    @Test
    public void getTitleTest() {
        assertEquals("Product1", product.getTitle());
    }

    @Test
    public void setDescriptionGoodTest() {
        product.setDescription("Product2 Description");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescriptionBadTest0() {
        product.setDescription(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescriptionBadTest1() {
        product.setDescription("");
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("Product1 Description", product.getDescription());
    }

    @Test
    public void testHashCodeTest() {
        assertEquals("Product1".hashCode() + "Product1 Description".hashCode(), product.hashCode());
    }

    @Test
    public void testEqualsTest() {
        Product product2 = product;
        assertEquals(product2, product);
    }

    @Test
    public void testToStringTest() {
        assertEquals("Title: Product1, Description: Product1 Description", product.toString());
    }
}