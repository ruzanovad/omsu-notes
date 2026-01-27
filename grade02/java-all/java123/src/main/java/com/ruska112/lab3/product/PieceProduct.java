package com.ruska112.lab3.product;

public class PieceProduct extends Product {
    protected double weight;

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("PieceProduct setWeight: weight less than 0");
        }
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public PieceProduct() {
        super("PieceProduct", "Description");
        setWeight(100);
    }

    public PieceProduct(PieceProduct pieceProduct) {
        super(pieceProduct.getTitle(), pieceProduct.getDescription());
        setWeight(pieceProduct.getWeight());
    }

    public PieceProduct(String title, String description, double weight) {
        super(title, description);
        setWeight(weight);
    }

    public int hashCode() {
        int result = super.hashCode();
        result += (int) weight;
        return result;
    }

    public boolean equals(PieceProduct pieceProduct) {
        if (pieceProduct == null) {
            return false;
        }
        return this.hashCode() == pieceProduct.hashCode();
    }

    public String toString() {
        return String.format("Title: %s\nDescription: %s\nWeight: %.2f\n", getTitle(), getDescription(), weight);
    }
}
