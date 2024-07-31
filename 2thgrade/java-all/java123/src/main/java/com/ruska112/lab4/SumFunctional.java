package com.ruska112.lab4;

public class SumFunctional<T extends IOneRealArgument> implements IFunctional<T> {
    @Override
    public double calculate(T function) {
        return function.solve(function.getLeftLimit()) + function.solve(function.getRightLimit()) + function.solve((function.getRightLimit() - function.getLeftLimit()) / 2);
    }
}
