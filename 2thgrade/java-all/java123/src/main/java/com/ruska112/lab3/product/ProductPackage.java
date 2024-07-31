package com.ruska112.lab3.product;

public class ProductPackage {
    private String title;
    private double weight;

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("ProductPackage setTitle: title is null");
        }
        if ("".equals(title)) {
            throw new IllegalArgumentException("ProductPackage setTitle: title is empty");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("ProductPackage setWeight: weight less than 0");
        }
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public ProductPackage(String title, double weight) {
        setTitle(title);
        setWeight(weight);
    }

    public int hashCode() {
        int result = title.hashCode();
        result += (int)weight;
        return result;
    }

    public boolean equals(ProductPackage productPackage) {
        if (productPackage == null) {
            return false;
        }
        return hashCode() == productPackage.hashCode();
    }

    public String toString() {
        return String.format("Title: %s, Weight: %.2f", title, weight);
    }
}
