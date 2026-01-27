package com.ruska112.lab3.service;

import com.ruska112.lab3.filter.BeginStringFilter;
import com.ruska112.lab3.filter.ContainsStringFilter;
import com.ruska112.lab3.filter.EndStringFilter;
import com.ruska112.lab3.product.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.ruska112.lab3.service.ProductService.*;

public class ProductServiceTest {
    ProductBatch productBatch;
    ProductBatch weightBatch;

    BeginStringFilter beginStringFilter;
    ContainsStringFilter containsStringFilter;
    EndStringFilter endStringFilter;

    @Before
    public void setup() {
        PackagedWeightProduct packagedWeightProduct = new PackagedWeightProduct(new Product("Product1", "Description1"), 100, new ProductPackage("pp1", 100));
        PackagedPieceProduct packagedPieceProduct = new PackagedPieceProduct(new PieceProduct("Product2", "Description2", 100), 100, new ProductPackage("pp2", 100));

        PackagedWeightProduct p0 = new PackagedWeightProduct(new Product("Product1", "Description1"), 100, new ProductPackage("pp1", 100));
        PackagedPieceProduct p1 = new PackagedPieceProduct(new PieceProduct("Product2", "Description2", 100), 100, new ProductPackage("pp2", 100));
        PackagedProduct p2 = new PackagedProduct(new WeightProduct("Product3", "Description3"), new ProductPackage("pp3", 100));

        PackagedProductSet packagedProductSet = new PackagedProductSet("Set", "Description Set", new ProductPackage("Set Pack", 100), p0, p1, p2);
        PackagedProductSet weightSet = new PackagedProductSet("Weight Set", "w set", new ProductPackage("w pack", 100), p0);

        productBatch = new ProductBatch("Desc", packagedWeightProduct, packagedPieceProduct, packagedProductSet);
        weightBatch = new ProductBatch("Desc", packagedWeightProduct, weightSet);
    }

    @Test
    public void countByFilterTest0() {
        endStringFilter = new EndStringFilter("1");
        assertEquals(1, countByFilter(productBatch, endStringFilter));
    }

    @Test
    public void countByFilterDeepTest0() {
        endStringFilter = new EndStringFilter("1");
        assertEquals(2, countByFilterDeep(productBatch, endStringFilter));
    }

    @Test
    public void checkAllWeightedGoodTest() {
        assertTrue(checkAllWeighted(weightBatch));
    }

    @Test
    public void countByFilterTest1() {
        beginStringFilter = new BeginStringFilter("Product");
        assertEquals(2, countByFilter(productBatch, beginStringFilter));
    }

    @Test
    public void countByFilterDeepTest1() {
        beginStringFilter = new BeginStringFilter("Product");
        assertEquals(5, countByFilterDeep(productBatch, beginStringFilter));
    }

    @Test
    public void checkAllWeightedBadTest() {
        assertFalse(checkAllWeighted(productBatch));
    }
}