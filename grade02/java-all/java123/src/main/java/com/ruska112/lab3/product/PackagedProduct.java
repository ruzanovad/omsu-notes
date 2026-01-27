package com.ruska112.lab3.product;

import java.util.Objects;

public class PackagedProduct extends Product {
    public ProductPackage productPackage;

    public ProductPackage getProductPackage() {
        return productPackage;
    }

    public void setProductPackage(ProductPackage productPackage) {
        if (productPackage == null) {
            throw new IllegalArgumentException("PackagedProduct setProductPackage: argument is null");
        }
        this.productPackage = productPackage;
    }

    public PackagedProduct() {
        super("PackagedProduct", "PackagedProduct Description");
        setProductPackage(new ProductPackage("ProductPackage", 100));
    }

    public PackagedProduct(PackagedProduct packagedProduct) {
        super(packagedProduct.getTitle(), packagedProduct.getDescription());
        setProductPackage(packagedProduct.getProductPackage());
    }

    public PackagedProduct(Product product, ProductPackage productPackage) {
        super(product);
        setProductPackage(productPackage);
    }

    public PackagedProduct(String title, String description, ProductPackage productPackage) {
        super(title, description);
        setProductPackage(productPackage);
    }

    public int hashCode() {
        return super.hashCode() + productPackage.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagedProduct that = (PackagedProduct) o;
        return this.hashCode() == that.hashCode();
    }

    public String toString() {
        return super.toString() + productPackage.toString();
    }
}
