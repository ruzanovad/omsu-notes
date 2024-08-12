package com.ruska112.lab3.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductBatchTest {

    ProductBatch productBatch;

    @Before
    public void setup() {
        PackagedWeightProduct packagedWeightProduct = new PackagedWeightProduct(new Product("PieceProduct", "Description"), 100, new ProductPackage("ProductPackage", 100));
        PackagedPieceProduct packagedPieceProduct = new PackagedPieceProduct(new PieceProduct("Product", "Description", 100), 100, new ProductPackage("ProductPackage", 100));
        productBatch = new ProductBatch("Description", packagedPieceProduct, packagedWeightProduct);
    }

    @Test
    public void getWeight() {
        int result = 100 + 100 + 100 * 100 + 100;
        assertEquals(result, productBatch.getWeight(), 0.1);
    }

    @Test
    public void getProductsTest() {
        PackagedWeightProduct packagedWeightProduct = new PackagedWeightProduct(new Product("PieceProduct", "Description"), 100, new ProductPackage("ProductPackage", 100));
        PackagedPieceProduct packagedPieceProduct = new PackagedPieceProduct(new PieceProduct("Product", "Description", 100), 100, new ProductPackage("ProductPackage", 100));
        PackagedProduct[] products = new PackagedProduct[] {packagedPieceProduct, packagedWeightProduct};
        assertArrayEquals(products, productBatch.getProducts());
    }
}