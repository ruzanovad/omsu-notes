package com.ruska112.lab3.product;

import java.util.Arrays;

public class ProductBatch {
    private String description;
    private PackagedProduct[] products;

    public ProductBatch(String description, PackagedProduct... products) {
        if (description == null) throw new IllegalArgumentException();
        this.description = description;
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public PackagedProduct[] getProducts() {
        return products;
    }

    public double getWeight() {
        double result = 0;
        for (PackagedProduct product : products) {
            if (product instanceof PackagedPieceProduct packagedPieceProduct) {
                result += packagedPieceProduct.getGrossWeight();
            }
            if (product instanceof PackagedWeightProduct packagedWeightProduct) {
                result += packagedWeightProduct.getGrossWeight();
            }
        }
        return result;
    }

    public int hashCode() {
        return description.hashCode() + Arrays.hashCode(products);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductBatch that)) return false;
        return this.hashCode() == that.hashCode();
    }

    public String toString() {
        return String.format("Product Batch: %s \nProducts: %s", description, Arrays.toString(products));
    }
}
