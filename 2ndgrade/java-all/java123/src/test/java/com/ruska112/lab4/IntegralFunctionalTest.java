package com.ruska112.lab4;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntegralFunctionalTest {

    @Test(expected = IllegalArgumentException.class)
    public void integralFunctionalExceptionTestLinear() {
        LinearFunction linearFunction = new LinearFunction(1, 1, 0, 10);
        IntegralFunctional<LinearFunction> linearFunctionIntegralFunctional = new IntegralFunctional<>(-10, 10);
        assertEquals(60.0, linearFunctionIntegralFunctional.calculate(linearFunction), 0.001);
    }

    @Test
    public void integralFunctionalTestLinear() {
        LinearFunction linearFunction = new LinearFunction(1, 1, 0, 10);
        IntegralFunctional<LinearFunction> linearFunctionIntegralFunctional = new IntegralFunctional<>(0, 10);
        assertEquals(60.0, linearFunctionIntegralFunctional.calculate(linearFunction), 0.001);
    }

    @Test
    public void integralFunctionalTestSin() {
        SinFunction sinFunction = new SinFunction(1, 1, 0, Math.PI);
        IntegralFunctional<SinFunction> sinFunctionIntegralFunctional = new IntegralFunctional<>(0, Math.PI);
        assertEquals(2.0, sinFunctionIntegralFunctional.calculate(sinFunction), 0.001);
    }

    @Test
    public void integralFunctionalTestFractionalLinear() {
        FractionalLinearFunction fractionalLinearFunction = new FractionalLinearFunction(1, 1, 1, 1, 0, 10);
        IntegralFunctional<FractionalLinearFunction> fractionalLinearFunctionIntegralFunctional = new IntegralFunctional<>(0, 10);
        assertEquals(10.0, fractionalLinearFunctionIntegralFunctional.calculate(fractionalLinearFunction), 0.001);
    }

    @Test
    public void integralFunctionalTestExp() {
        ExpFunction expFunction = new ExpFunction(1, 0, 0, 1);
        IntegralFunctional<ExpFunction> expFunctionIntegralFunctional = new IntegralFunctional<>(0, 1);
        assertEquals(1.7183, expFunctionIntegralFunctional.calculate(expFunction), 0.001);
    }
}