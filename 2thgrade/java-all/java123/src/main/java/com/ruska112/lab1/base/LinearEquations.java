package com.ruska112.lab1.base;

public class LinearEquations {
    // a1*x + b1*y = c1
    // a2*x + b2*y = c2
    public double a1, a2, b1, b2, c1, c2;

    public LinearEquations(double a1, double b1, double c1, double a2, double b2, double c2) {
        this.a1 = a1;
        this.b1 = b1;
        this.c1 = c1;
        this.a2 = a2;
        this.b2 = b2;
        this.c2 = c2;
    }

    public void Solve() {
        double x;
        double y;
        double delta, delta1, delta2;
        delta = (a1 * b2) - (b1 * a2);
        delta1 = (c1 * b2) - (b1 * c2);
        delta2 = (a1 * c2) - (c1 * a2);
        if (delta1 == 0 && delta2 == 0 && delta != 0) {
            System.out.println("Бесконечно много решений");
        } else if (delta != 0) {
            x = delta1 / delta;
            y = delta2 / delta;
            System.out.println("y = " + y);
            System.out.println("x = " + x);
        } else {
            if ((a2 / a1) == (b2 / b1) && (b2 / b1) == (c2 / c1)) {
                System.out.println("y = " + (-a1 / b1) + "*x + (" + (c1 / b1) + ")");
            } else {
                if (a1 != 0 && b1 != 0 && c1 == 0) {
                    System.out.println("y = " + (-a1 / b1) + "*x + (" + (c1 / b1) + ")");
                } else if (a1 == 0 && b1 == 0 && c1 != 0 && a2 != 0 && b2 != 0 && c2 != 0) {
                    System.out.println("Решений нет");
                } else if (a2 == 0 && b2 == 0 && c2 != 0 && a1 != 0 && b1 != 0 && c1 != 0) {
                    System.out.println("Решений нет");
                } else if (a2 != 0 && b2 != 0 && c2 == 0) {
                    System.out.println("y = " + (-a2 / b2) + "*x + (" + (c2 / b2) + ")");
                } else if (a1 == 0 && b1 == 0 && a2 == 0 && b2 == 0) {
                    System.out.println("Нет решений");
                }
            }
        }
    }
}
