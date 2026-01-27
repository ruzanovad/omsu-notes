package com.ruska112.lab4;

public interface IFunctional<T extends IOneRealArgument> {
    double calculate(T function);
}
