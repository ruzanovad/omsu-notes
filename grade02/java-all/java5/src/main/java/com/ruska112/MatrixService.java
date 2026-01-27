package com.ruska112;

import java.util.Arrays;

public class MatrixService {
    public static void arrangeMatrices(Matrix[] matrices, MatrixComparator comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException();
        }
        if (matrices == null) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(matrices, comparator);
    }
}
