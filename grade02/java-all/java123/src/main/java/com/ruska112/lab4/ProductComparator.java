package com.ruska112.lab4;

import com.ruska112.lab3.product.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product product, Product t1) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        if (t1 == null) {
            throw new IllegalArgumentException();
        }
        int i = product.getTitle().compareToIgnoreCase(t1.getTitle());
        if (i != 0) {
            return i;
        }
        return product.getDescription().compareToIgnoreCase(t1.getDescription());
    }


}
