package com.ruska112.lab4;

public class ExpFunction implements IOneRealArgument {
    private double a;
    private double b;

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

    public ExpFunction(double a, double b, double leftLimit, double rightLimit) {
        if (leftLimit > rightLimit) {
            throw new IllegalArgumentException("Bad limits left less than right");
        }

        this.a = a;
        this.b = b;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    @Override
    public double solve(double x) {
        if (x < leftLimit || x > rightLimit) {
            throw new IllegalArgumentException();
        }
        return (a * Math.pow(Math.E, x) + b);
    }
}
