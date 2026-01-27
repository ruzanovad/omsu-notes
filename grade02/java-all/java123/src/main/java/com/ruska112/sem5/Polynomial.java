package com.ruska112.sem5;

public class Polynomial {
    private double[] odds;

    public double[] getOdds() {
        return odds;
    }

    public Polynomial(double... odds) {
        if (odds == null) {
            throw new IllegalArgumentException();
        }
        this.odds = odds;
    }
}
