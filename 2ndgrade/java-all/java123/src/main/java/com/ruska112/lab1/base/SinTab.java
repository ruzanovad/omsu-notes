package com.ruska112.lab1.base;

public class SinTab {
    public double a;
    public double b;
    public double step;

    public SinTab(double a, double b, double step) {
        this.a = a;
        this.b = b;
        this.step = step;
    }

    public void Tab() {
        if (a > b) {
            double tmp = a;
            a = b;
            b = tmp;
        }
        if (a <= b && step > 0) {
            double x = a;
            double y;
            do {
                y = Math.sin(x);
                System.out.printf("x = %f\nsin(%f) = %f\n", x, x, y);
                x += step;
            } while (x <= b + step && step != 0);
        } else {
            double x = b;
            double y;
            do {
                y = Math.sin(x);
                System.out.printf("x = %f\nsin(%f) = %f\n", x, x, y);
                x += step;
            } while (x >= a + step && step != 0);
        }
    }
}
