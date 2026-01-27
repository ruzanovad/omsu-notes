package com.ruska112;

import java.util.Comparator;

public class MatrixComparator implements Comparator<Matrix> {
    @Override
    public int compare(Matrix matrix, Matrix t1) {
        if (matrix == null) {
            throw new IllegalArgumentException();
        }
        if (t1 == null) {
            throw new IllegalArgumentException();
        }
        return Double.compare(matrix.getDeterminant(), t1.getDeterminant());
    }
}