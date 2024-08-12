package com.ruska112.lab3.service;

import com.ruska112.lab3.filter.IFilter;
import com.ruska112.lab3.product.PackagedProduct;
import com.ruska112.lab3.product.PackagedProductSet;
import com.ruska112.lab3.product.PackagedWeightProduct;
import com.ruska112.lab3.product.ProductBatch;

public class ProductService {

    public static int countByFilter(ProductBatch productBatch, IFilter filter) {
        if (productBatch == null) {
            throw new IllegalArgumentException("ProductService countByFilter: productBatch argument is null");
        }
        if (filter == null) {
            throw new IllegalArgumentException("ProductService countByFilter: filter argument is null");
        }
        int result = 0;
        for (PackagedProduct product : productBatch.getProducts()) {
            if (filter.apply(product.getTitle())) {
                result++;
            }
        }
        return result;
    }

    public static int countByFilterDeep(ProductBatch productBatch, IFilter filter) {
        if (productBatch == null) {
            throw new IllegalArgumentException("ProductService countByFilterDeep: productBatch argument is null");
        }
        if (filter == null) {
            throw new IllegalArgumentException("ProductService countByFilterDeep: filter argument is null");
        }
        int result = 0;
        for (PackagedProduct product : productBatch.getProducts()) {
            if (product instanceof PackagedProductSet packagedProductSet) {
                for (PackagedProduct productFromSet : packagedProductSet.getProducts()) {
                    if (filter.apply(productFromSet.getTitle())) {
                        result++;
                    }
                }
            } else {
                if (filter.apply(product.getTitle())) {
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean checkAllWeighted(ProductBatch productBatch) {
        if (productBatch == null) {
            throw new IllegalArgumentException("ProductService checkAllWeighted: productBatch argument is null");
        }
        boolean result = true;
        for (PackagedProduct product : productBatch.getProducts()) {
            if (product instanceof PackagedProductSet packagedProductSet) {
                for (PackagedProduct productFromSet : packagedProductSet.getProducts()) {
                    if (!(productFromSet instanceof PackagedWeightProduct)) {
                        result = false;
                        break;
                    }
                }
            } else if (!(product instanceof PackagedWeightProduct)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
