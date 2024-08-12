package com.ruska112.lab4;

public class FractionalLinearFunction implements IOneRealArgument {
    private double a;
    private double b;
    private double c;
    private double d;

    private double leftLimit;
    private double rightLimit;

    @Override
    public double getLeftLimit() {
        return leftLimit;
    }

    @Override
    public double getRightLimit() {
        return rightLimit;
    }

    public FractionalLinearFunction(double a, double b, double c, double d, double leftLimit, double rightLimit) {
        if (leftLimit > rightLimit) {
            throw new IllegalArgumentException("Bad limits left less than right");
        }
        if (c == 0 && d == 0) {
            throw new IllegalArgumentException();
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    @Override
    public double solve(double x) {
        if (x < leftLimit || x > rightLimit) {
            throw new IllegalArgumentException();
        }
        if ((c * x + d) == 0) {
            throw new IllegalArgumentException("denominator equals 0");
        }
        return (a * x + b) / (c * x + d);
    }
}
