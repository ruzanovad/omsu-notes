package com.ruska112.lab4;

import com.ruska112.lab3.product.*;

import static com.ruska112.lab4.ComparatorDemo.sortGoods;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComparatorDemoTest {
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
    public void comparatorDemoTest0() {
        Product[] products = {weightProduct, pieceProduct, product};
        Product[] goods = {product, pieceProduct, weightProduct};
        sortGoods(productComparator, products);
        assertArrayEquals(goods, products);
    }

    @Test
    public void comparatorDemoTest1() {
        Product[] products = {weightProduct, pieceProduct, product, packagedProduct};
        Product[] goods = {product, packagedProduct, pieceProduct, weightProduct};
        sortGoods(productComparator, products);
        assertArrayEquals(goods, products);
    }

    @Test
    public void comparatorDemoTest2() {
        Product[] products = {weightProduct, pieceProduct, packagedProduct, product};
        Product[] goods = {packagedProduct, product, pieceProduct, weightProduct};
        sortGoods(productComparator, products);
        assertArrayEquals(goods, products);
    }

}