package com.ruska112.lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class SumFunctionalTest {

    @Test
    public void sumFunctionalTestLinear0() {
        LinearFunction linearFunction = new LinearFunction(1, 0, 0, 10);
        SumFunctional<LinearFunction> linearFunctionSumFunctional = new SumFunctional<>();
        assertEquals(15.0, linearFunctionSumFunctional.calculate(linearFunction), 0.001);
    }

    @Test
    public void sumFunctionalTestLinear1() {
        LinearFunction linearFunction = new LinearFunction(1, 1, 0, 20);
        SumFunctional<LinearFunction> linearFunctionSumFunctional = new SumFunctional<>();
        assertEquals(33.0, linearFunctionSumFunctional.calculate(linearFunction), 0.001);
    }

    @Test
    public void sumFunctionalTestSin0() {
        SinFunction sinFunction = new SinFunction(1, 1, 0, 2 * Math.PI);
        SumFunctional<SinFunction> sinFunctionSumFunctional = new SumFunctional<>();
        assertEquals(0.0, sinFunctionSumFunctional.calculate(sinFunction), 0.001);
    }

    @Test
    public void sumFunctionalTestSin1() {
        SinFunction sinFunction = new SinFunction(1, 1, 0, Math.PI);
        SumFunctional<SinFunction> sinFunctionSumFunctional = new SumFunctional<>();
        assertEquals(1.0, sinFunctionSumFunctional.calculate(sinFunction), 0.001);
    }

    @Test
    public void sumFunctionalTestFractionalLinear0() {
        FractionalLinearFunction fractionalLinearFunction = new FractionalLinearFunction(1, 1, 1, 1, 0, 10);
        SumFunctional<FractionalLinearFunction> fractionalLinearFunctionSumFunctional = new SumFunctional<>();
        assertEquals(3.0, fractionalLinearFunctionSumFunctional.calculate(fractionalLinearFunction), 0.001);
    }

    @Test
    public void sumFunctionalTestFractionalLinear1() {
        FractionalLinearFunction fractionalLinearFunction = new FractionalLinearFunction(1, 2, 3, 4, 0, 10);
        SumFunctional<FractionalLinearFunction> fractionalLinearFunctionSumFunctional = new SumFunctional<>();
        assertEquals(1.2214, fractionalLinearFunctionSumFunctional.calculate(fractionalLinearFunction), 0.001);
    }

    @Test
    public void sumFunctionalTestExp0() {
        ExpFunction expFunction = new ExpFunction(1, 0, 0, 2);
        SumFunctional<ExpFunction> expFunctionSumFunctional = new SumFunctional<>();
        assertEquals(11.1073, expFunctionSumFunctional.calculate(expFunction), 0.001);
    }

    @Test
    public void sumFunctionalTestExp1() {
        ExpFunction expFunction = new ExpFunction(1, 1, 0, 2);
        SumFunctional<ExpFunction> expFunctionSumFunctional = new SumFunctional<>();
        assertEquals(14.1073, expFunctionSumFunctional.calculate(expFunction), 0.001);
    }
}