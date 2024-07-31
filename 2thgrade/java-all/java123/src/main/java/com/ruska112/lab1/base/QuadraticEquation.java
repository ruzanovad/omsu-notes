package com.ruska112.lab1.base;

public class QuadraticEquation {
    // a*x^2 + b*x + c = 0
    public double a;
    public double b;
    public double c;

    private double X1;
    private double X2;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void Analysis() {
        int rootCount = 0;
        double D = (b * b) - (4 * a * c);

        if (D > 0) {
            if (a != 0) {
                if (Math.sqrt(-c / a) != 0) {
                    rootCount = 2;
                    X1 = -Math.sqrt(-c / a);
                    X2 = -X1;
                    System.out.printf("Количество корней = %d\n", rootCount);
                    System.out.printf("X1 = %f\n", X1);
                    System.out.printf("X2 = %f\n", X2);
                } else {
                    rootCount = 1;
                    X1 = 0;
                    X2 = -b / a;
                    System.out.printf("Количество корней = %d\n", rootCount);
                    System.out.printf("X1 = %f\n", X1);
                    System.out.printf("X2 = %f\n", X2);
                }
            } else if (b != 0) {
                rootCount = 1;
                X1 = c / b;
                X2 = X1;
                System.out.printf("Количество корней = %d\n", rootCount);
                System.out.printf("X1 = %f\n", X1);
                System.out.printf("X2 = %f\n", X2);
            } else if (c != 0) {
                System.out.println("Решений нет");
            }
        } else if (D < 0) {
            System.out.println("Вещественных корней нет");
        } else {
            if (a != 0) {
                rootCount = 1;
                X1 = 0;
                X2 = X1;
                System.out.printf("Количество корней = %d\n", rootCount);
                System.out.printf("X = %f\n", X1);
            } else if (b != 0) {
                rootCount = 1;
                X1 = -b / 2 * a;
                X2 = X1;
                System.out.printf("Количество корней = %d\n", rootCount);
                System.out.printf("X1 = %f\n", X1);
                System.out.printf("X2 = %f\n", X2);
            } else if (c != 0) {
                System.out.println("Решений нет");
            } else {
                System.out.println("Бесконечное множество решений");
            }
        }
    }
}
