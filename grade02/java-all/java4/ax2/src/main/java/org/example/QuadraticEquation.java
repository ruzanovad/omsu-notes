package org.example;

import java.util.ArrayList;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public ArrayList<Double> solve() throws NoRootsException {
        ArrayList<Double> roots = new ArrayList<>();
        if (a == 0) {
            if (b == 0) {
                throw new NoRootsException("a == 0, b == 0");
            } else {
                roots.add(-c / b);
            }
        } else {
            double D = (b * b) - (4 * a * c);
            if (D > 0) {
                roots.add((-b + Math.sqrt(D)) / (2 * a));
                roots.add((-b - Math.sqrt(D)) / (2 * a));
            } else if (D == 0) {
                roots.add((-b) / (2 * a));
            } else {
                throw new NoRootsException("No rational roots, discriminant less than 0");
            }
        }
        return roots;
    }
}
