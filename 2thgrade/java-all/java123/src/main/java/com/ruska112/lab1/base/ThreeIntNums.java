package com.ruska112.lab1.base;

public class ThreeIntNums {
    public int FirstNum;
    public int SecondNum;
    public int ThirdNum;

    public ThreeIntNums(int FirstNum, int SecondNum, int ThirdNum) {
        this.FirstNum = FirstNum;
        this.SecondNum = SecondNum;
        this.ThirdNum = ThirdNum;
    }

    public int Composition() {
        return (FirstNum * SecondNum * ThirdNum);
    }

    public double Average() {
        return FirstNum + SecondNum + ThirdNum / 3.0;
    }

    public void GetGrowthSequence() {
        int tmp;
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
        System.out.printf("%d, %d, %d\n", FirstNum, SecondNum, ThirdNum);
    }
}
