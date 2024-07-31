package com.ruska112;

import java.util.Arrays;
import java.util.Objects;

public class Matrix implements IMatrix {
    protected int size;
    protected double[] array;

    protected boolean actualDeterminant = false;

    protected double determinant = 1;

    public double getIJ(int i, int j) {
        if (i >= 0 && i < size && j >= 0 && j < size) {
            return array[i * size + j];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setIJ(int i, int j, double element) {
        if (i >= 0 && i < size && j >= 0 && j < size) {
            array[i * size + j] = element;
            actualDeterminant = false;
        } else {
            throw new IllegalArgumentException();
        }
    }

    static void swap(double[] arr, int size, int i1, int j1, int i2, int j2) {
        double temp = arr[i1 * size + j1];
        arr[i1 * size + j1] = arr[i2 * size + j2];
        arr[i2 * size + j2] = temp;
    }

    public double getDeterminant() {
        if (!actualDeterminant) {
            actualDeterminant = true;
            determinant = 1;
            double[] tmpArray = Arrays.copyOf(array, size * size);
            double coefficient = 0;
            int i, j, k, s, index;
            for (i = 0; i < size - 1; i++) {
                index = i;
                while (index < size && tmpArray[index * size + i] == 0) {
                    index++;
                }
                if (index == size) {
                    actualDeterminant = false;
                    determinant = 0;
                    return determinant;
                }
                if (index != i) {
                    for (s = 0; s < size; s++) {
                        swap(tmpArray, size, index, s, i, s);
                    }
                    determinant *= Math.pow(-1, index - i);
                }
                for (j = i + 1; j < size; j++) {
                    coefficient = tmpArray[j * size + i] / tmpArray[i * size + i];
                    for (k = 0; k < size; k++) {
                        tmpArray[j * size + k] -= tmpArray[i * size + k] * coefficient;
                    }
                }
            }
            for (i = 0; i < size; i++) {
                determinant *= tmpArray[i * size + i];
            }
            return determinant;
        }
        return determinant;
    }

    public Matrix(int size) {
        if (size > 0) {
            this.size = size;
            this.array = new double[size * size];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getSize() {
        return size;
    }

    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    public boolean equals(Matrix obj) {
        return this.hashCode() == obj.hashCode();
    }
}
