package com.ruska112.lab3.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackagedProductSetTest {

    PackagedProductSet packagedProductSet;

    @Before
    public void setup() {
        PackagedWeightProduct packagedWeightProduct = new PackagedWeightProduct(new Product("PieceProduct", "Description"), 100, new ProductPackage("ProductPackage", 100));
        PackagedPieceProduct packagedPieceProduct = new PackagedPieceProduct(new PieceProduct("Product", "Description", 100), 100, new ProductPackage("ProductPackage", 100));
        packagedProductSet = new PackagedProductSet("PackagedProductSet", "Description", new ProductPackage("ProductPackage", 100), packagedWeightProduct, packagedPieceProduct);
    }

    @Test
    public void getGrossWeightTest() {
        int net = 100 + 100 * 100;
        assertEquals(net, packagedProductSet.getNetWeight(), 0.1);
    }

    @Test
    public void getNetWeightTest() {
        int gross = 100 + 100 + 100 * 100 + 100 + 100;
        assertEquals(gross, packagedProductSet.getGrossWeight(), 0.1);
    }
}