package com.ruska112.lab1.base;

public class ThreeFloatNums {
    public double FirstNum;
    public double SecondNum;
    public double ThirdNum;

    public ThreeFloatNums(double FirstNum, double SecondNum, double ThirdNum) {
        this.FirstNum = FirstNum;
        this.SecondNum = SecondNum;
        this.ThirdNum = ThirdNum;
    }

    public double Composition() {
        return (FirstNum * SecondNum * ThirdNum);
    }

    public double Average() {
        return FirstNum + SecondNum + ThirdNum / 3;
    }

    public void GetGrowthSequence() {
        double tmp;
        if (FirstNum > SecondNum) {
            tmp = FirstNum;
            FirstNum = SecondNum;
            SecondNum = tmp;
        }
        if (SecondNum > ThirdNum) {
            tmp = SecondNum;
            SecondNum = ThirdNum;
            ThirdNum = tmp;
        }
        if (FirstNum > SecondNum) {
            tmp = FirstNum;
            FirstNum = SecondNum;
            SecondNum = tmp;
        }
        System.out.printf("%f, %f, %f\n", FirstNum, SecondNum, ThirdNum);
    }
}
