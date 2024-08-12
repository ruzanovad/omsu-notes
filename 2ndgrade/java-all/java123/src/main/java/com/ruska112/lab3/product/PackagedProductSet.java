package com.ruska112.lab3.product;

public class PackagedProductSet extends PackagedProduct {
    private PackagedProduct[] products;

    public PackagedProductSet(String name, String description, ProductPackage productPackage, PackagedProduct... products) {
        super(name, description, productPackage);
        this.products = products;
    }

    public PackagedProduct[] getProducts() {
        return products;
    }

    public double getGrossWeight() {
        double result = 0;
        for (PackagedProduct product : products) {
            if (product instanceof PackagedPieceProduct packagedPieceProduct) {
                result += packagedPieceProduct.getGrossWeight();
            }
            if (product instanceof PackagedWeightProduct packagedWeightProduct) {
                result += packagedWeightProduct.getGrossWeight();
            }
        }
        result += productPackage.getWeight();
        return result;
    }

    public double getNetWeight() {
        double result = 0;
        for (PackagedProduct product : products) {
            if (product instanceof PackagedPieceProduct packagedPieceProduct) {
                result += packagedPieceProduct.getNetWeight();
            }
            if (product instanceof PackagedWeightProduct packagedWeightProduct) {
                result += packagedWeightProduct.getNetWeight();
            }
        }
        return result;
    }
}
