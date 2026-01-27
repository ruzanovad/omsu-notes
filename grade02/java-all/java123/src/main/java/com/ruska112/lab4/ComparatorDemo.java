package com.ruska112.lab4;

import com.ruska112.lab3.product.Product;

import java.util.Arrays;

public class ComparatorDemo {
    public static void sortGoods(ProductComparator comparator, Product... products) {
        if (comparator == null) {
            throw new IllegalArgumentException();
        }
        if (products == null) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(products, comparator);
    }
}
