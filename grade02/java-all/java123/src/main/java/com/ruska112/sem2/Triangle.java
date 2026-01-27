package com.ruska112.sem2;

import com.ruska112.lab1.c3d.Point3D;
import com.ruska112.lab1.c3d.Vector3D;

import static com.ruska112.lab1.c3d.Vector3DProcessor.isCollinear;

public class Triangle {
    private Point3D point1;
    private Point3D point2;
    private Point3D point3;

    public Triangle(Point3D point1, Point3D point2, Point3D point3) {
        if (point1 != null && point2 != null && point3 != null) {
            if (!point1.equals(point2) && !point2.equals(point3) && !point3.equals(point1)) {
                Vector3D v1 = new Vector3D(point1);
                Vector3D v2 = new Vector3D(point2);
                Vector3D v3 = new Vector3D(point3);
                if (!isCollinear(v1, v2) && !isCollinear(v2, v3) && !isCollinear(v1, v3)) {
                    this.point1 = point1;
                    this.point2 = point2;
                    this.point3 = point3;
                } else {
                    throw new IllegalArgumentException("Triangle constructor: collinear points");
                }
            } else {
                throw new IllegalArgumentException("Triangle constructor: equals points");
           }
        } else {
            throw new IllegalArgumentException("Triangle constructor: null point");
        }
    }

    public double getPerimeter() {
        double result = -1;
        if (this.point1 != null && this.point2 != null && this.point3 != null) {
            result = (point1.getDistance(point2) + point2.getDistance(point3) + point3.getDistance(point1));
        }
        return result;
    }

    public double getArea() {
        double result = -1;
        if (this.point1 != null && this.point2 != null && this.point3 != null) {
            double perimeter = this.getPerimeter() / 2;
            result = Math.sqrt(perimeter * (perimeter - this.point1.getDistance(point2)) * (perimeter - this.point2.getDistance(point3)) * (perimeter - this.point3.getDistance(point1)));
        }
        return result;
    }

    public Point3D getMedianPoint() {
        Point3D result = new Point3D();
        try {
            double x = (this.point1.getX() + this.point2.getX() + this.point3.getX()) / 3;
            double y = (this.point1.getY() + this.point2.getY() + this.point3.getY()) / 3;
            double z = (this.point1.getZ() + this.point2.getZ() + this.point3.getZ()) / 3;
            result.setX(x);
            result.setY(y);
            result.setZ(z);
        } catch (Exception e) {
            System.out.println("points == null");
        }
        return result;
    }
}
