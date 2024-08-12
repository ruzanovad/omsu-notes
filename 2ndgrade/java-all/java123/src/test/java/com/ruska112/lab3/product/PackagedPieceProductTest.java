package com.ruska112.lab3.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PackagedPieceProductTest {

    PackagedPieceProduct packagedPieceProduct;

    @Before
    public void setup() {
        packagedPieceProduct = new PackagedPieceProduct(new PieceProduct("Product", "Description", 100), 100, new ProductPackage("ProductPackage", 100));
    }

    @Test
    public void getNetWeight() {
        double net = 100*100;
        assertEquals(net, packagedPieceProduct.getNetWeight(), 0.1);
    }

    @Test
    public void getGrossWeight() {
        double gross = 100*100 + 100;
        assertEquals(gross, packagedPieceProduct.getGrossWeight(), 0.1);
    }
}