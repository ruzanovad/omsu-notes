package com.ruska112.lab1.base;

import java.util.Scanner;

public class Exp {
    public void main() {
        Scanner in = new Scanner(System.in);
        double x, eps, res = 1, taylor = 1;
        System.out.print("Enter x = ");
        x = in.nextDouble();
        System.out.print("Enter epsilon = ");
        eps = in.nextDouble();
        int fact = 1, i = 1;
        while (Math.abs(taylor) > eps) {
            taylor = (Math.pow(x, i) / fact);
            res += taylor;
            i++;
            fact *= i;
        }
        System.out.println("Result = " + res);
    }
}
