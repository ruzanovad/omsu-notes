package com.ruska112.lab1.c3d;

public class Vector3DArray {
    private final Vector3D[] vectorArr;

    public Vector3DArray(int size) {
        vectorArr = new Vector3D[size];
        for (int i = 0; i < vectorArr.length; i++) {
            vectorArr[i] = new Vector3D();
        }
    }

    public int length() {
        return vectorArr.length;
    }

    public void replaceElement(int index, Vector3D vector) {
        if ((index >= 0) && (index < vectorArr.length)) {
            if (vector != null) {
                vectorArr[index] = vector;
            }
        }
    }

    public double maxVectorLength() {
        double result = vectorArr[0].length();
        for (int i = 1; i < this.length(); i++) {
            if (result < vectorArr[i].length()) {
                result = vectorArr[i].length();
            }
        }
        return result;
    }

    public int indexThisVector(Vector3D vector3D) {
        int result = -1;
        if (vector3D != null) {
            for (int i = 0; i < this.length(); i++) {
                Vector3D vec = vectorArr[i];
                if (vector3D.equalsWith(vec)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    public Vector3D sumAllVectors() {
        Vector3D tmp = new Vector3D();
        for (Vector3D vec : vectorArr) {
            tmp = Vector3DProcessor.sumOfTwoVectors(tmp, vec);
        }
        return tmp;
    }

    public Vector3D linearCombination(double[] k) {
        Vector3D tmp = new Vector3D();
        try {
            if (k.length == this.length()) {
                for (int i = 0; i < this.length(); i++) {
                    Vector3D vec = new Vector3D((k[i] * vectorArr[i].getX()), (k[i] * vectorArr[i].getY()), (k[i] * vectorArr[i].getZ()));
                    tmp = Vector3DProcessor.sumOfTwoVectors(tmp, vec);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }

    public Point3D[] pointShift(Point3D point) {
        Point3D[] pointArr = new Point3D[this.length()];
        if (point != null) {
            for (int i = 0; i < this.length(); i++) {
                pointArr[i] = new Point3D((point.getX() + vectorArr[i].getX()), (point.getY() + vectorArr[i].getY()), (point.getZ() + vectorArr[i].getZ()));
            }
        }

        return pointArr;
    }

}
