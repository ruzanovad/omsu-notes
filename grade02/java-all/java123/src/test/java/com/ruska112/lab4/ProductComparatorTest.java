package com.ruska112.lab4;

import com.ruska112.lab3.product.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductComparatorTest {
    ProductComparator productComparator = new ProductComparator();
    Product product;
    WeightProduct weightProduct;
    PieceProduct pieceProduct;
    PackagedProduct packagedProduct;

    @Before
    public void setup() {
        product = new Product("Product", "Description");
        weightProduct = new WeightProduct("Product", "Weight Product Description");
        pieceProduct = new PieceProduct("Product", "Piece Product Description", 100);
        packagedProduct = new PackagedProduct("Product", "Description", new ProductPackage("pp", 100));
    }

    @Test
    public void compareTest0() {
        boolean b = (productComparator.compare(product, packagedProduct) == 0);
        assertTrue(b);
    }

    @Test
    public void compareTest1() {
        boolean b = (productComparator.compare(product, weightProduct) == 0);
        assertFalse(b);
    }

    @Test
    public void compareTest2() {
        boolean b = (productComparator.compare(pieceProduct, weightProduct) == 0);
        assertFalse(b);
    }
}