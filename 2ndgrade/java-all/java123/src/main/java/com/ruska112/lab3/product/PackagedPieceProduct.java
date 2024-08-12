package com.ruska112.lab3.product;

public class PackagedPieceProduct extends PackagedProduct {
    private int amount;
    private PieceProduct product;

    public void setAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("PackagedPieceProduct setAmount: amount less or equals 0");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public PackagedPieceProduct(PieceProduct product, int amount, ProductPackage productPackage) {
        super(product, productPackage);
        if (product == null) {
            throw new IllegalArgumentException();
        }
        setAmount(amount);
        this.product = product;
    }

    public double getNetWeight() {
        return amount * product.getWeight();
    }

    public double getGrossWeight() {
        return amount * product.getWeight() + productPackage.getWeight();
    }

    public int hashCode() {
        return super.hashCode() + productPackage.hashCode() + amount;
    }

    public boolean equals(PackagedPieceProduct packagedPieceProduct) {
        return hashCode() == packagedPieceProduct.hashCode();
    }

    public String toString() {
        return String.format("Title: %s\nDescription: %s\nWeight: %f\nAmount: %d\nPackage Title: %s\nPackage Weight: %f\n", title, description, product.getWeight(), amount, productPackage.getTitle(), productPackage.getWeight());
    }
}
