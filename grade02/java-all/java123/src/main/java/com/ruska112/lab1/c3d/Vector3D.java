package com.ruska112.lab1.c3d;

public class Vector3D {
    protected Point3D endPoint;

    public Vector3D() {
        endPoint = new Point3D();
    }

    public Vector3D(Point3D point) {
        if (point != null) {
            endPoint = point;
        }
    }

    public Vector3D(double x, double y, double z) {
        endPoint = new Point3D(x, y, z);
    }

    public Vector3D(Point3D fPoint, Point3D sPoint) {
        if ((fPoint != null) && (sPoint != null)) {
            endPoint = new Point3D((sPoint.getX() - fPoint.getX()), (sPoint.getY() - fPoint.getY()), (sPoint.getZ() - fPoint.getZ()));
        }
    }

    public double getX() {
        return this.endPoint.getX();
    }

    public double getY() {
        return this.endPoint.getY();
    }

    public double getZ() {
        return this.endPoint.getZ();
    }

    public double length() {
        double x2 = Math.pow(endPoint.getX(), 2);
        double y2 = Math.pow(endPoint.getY(), 2);
        double z2 = Math.pow(endPoint.getZ(), 2);
        return Math.sqrt(x2 + y2 + z2);
    }

    public boolean equalsWith(Vector3D vector3D) {
        boolean result = false;
        boolean len = this.length() == vector3D.length();
        if (len && (this.endPoint.getX() / vector3D.endPoint.getX()) == (this.endPoint.getY() / vector3D.endPoint.getY()) && (this.endPoint.getY() / vector3D.endPoint.getY()) == (this.endPoint.getZ() / vector3D.endPoint.getZ())) {
            result = true;
        }
        return result;
    }
}
