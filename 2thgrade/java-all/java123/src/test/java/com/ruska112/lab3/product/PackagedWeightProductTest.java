package com.ruska112.lab3.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackagedWeightProductTest {

    PackagedWeightProduct packagedWeightProduct;

    @Before
    public void setup() {
        packagedWeightProduct = new PackagedWeightProduct(new Product("PieceProduct", "Description"), 100,  new ProductPackage("ProductPackage", 100));
    }

    @Test
    public void getNetWeightTest() {
        double net = 100;
        assertEquals(net, packagedWeightProduct.getWeight(), 0.1);
    }

    @Test
    public void getGrossWeightTest() {
        double gross = 100 + 100;
        assertEquals(gross, packagedWeightProduct.getGrossWeight(), 0.1);
    }
}