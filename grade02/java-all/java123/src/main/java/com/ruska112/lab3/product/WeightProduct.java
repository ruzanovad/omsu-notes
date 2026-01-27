package com.ruska112.lab3.product;

public class WeightProduct extends Product {
    public WeightProduct() {
        super("WeightProduct", "Description");
    }

    public WeightProduct(WeightProduct weightProduct) {
        super(weightProduct.getTitle(), weightProduct.getDescription());
    }

    public WeightProduct(String title, String description) {
        super(title, description);
    }
}
