package com.ruska112.lab3.product;

public class PackagedWeightProduct extends PackagedProduct {
    private double weight;

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("PackagedWeightProduct setWeight: weight less or equals 0");
        }
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public PackagedWeightProduct(Product product, double weight, ProductPackage productPackage) {
        super(product, productPackage);
        setWeight(weight);
    }


    public double getNetWeight() {
        return weight;
    }

    public double getGrossWeight() {
        return weight + productPackage.getWeight();
    }

    public int hashCode() {
        return super.hashCode() + productPackage.hashCode() + (int) weight;
    }

    public boolean equals(PackagedWeightProduct packagedWeightProduct) {
        return hashCode() == packagedWeightProduct.hashCode();
    }


}
