package com.ruska112.lab2;

import com.ruska112.sem4.MyDate;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test(expected = IllegalArgumentException.class)
    public void paymentConstructorTestFullNameError() {
        String fullName = null;
        MyDate date = new MyDate(2000, 1, 1);
        int sum = 100_000_000;
        Payment payment = new Payment(fullName, date, sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void paymentConstructorTestDateError() {
        String fullName = new String("Ded");
        MyDate date = null;
        int sum = 100_000_000;
        Payment payment = new Payment(fullName, date, sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void paymentConstructorTestSumError() {
        String fullName = new String("Ded");
        MyDate date = new MyDate(2000, 1, 1);
        int sum = -100_000_000;
        Payment payment = new Payment(fullName, date, sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void paymentEqualsTest() {
        String fullName = new String("Ded");
        MyDate date = new MyDate(2000, 1, 1);
        int sum = 100_000_000;
        Payment payment1 = new Payment(fullName, date, sum);
        Payment payment2 = null;

        payment1.equals(payment2);
    }

    @Test
    public void paymentEqualsTestTrue() {
        String fullName = new String("Ded");
        MyDate date = new MyDate(2000, 1, 1);
        int sum = 100_000_000;
        Payment payment1 = new Payment(fullName, date, sum);
        Payment payment2 = new Payment(fullName, date, sum);

        assertTrue(payment1.equals(payment2));
    }

    @Test
    public void paymentEqualsTestFalse() {
        String fullName = new String("Ded");
        MyDate date = new MyDate(2000, 1, 1);
        int sum1 = 100_000_000;
        int sum2 = 100_000;
        Payment payment1 = new Payment(fullName, date, sum1);
        Payment payment2 = new Payment(fullName, date, sum2);

        assertFalse(payment1.equals(payment2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void paymentSetFullNameTest() {
        Payment payment = new Payment("Ded", new MyDate(2000, 1, 1), 100_000);
        payment.setFullName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void paymentSetMyDateBadTest() {
        Payment payment = new Payment("Ded", new MyDate(2000, 1, 1), 100_000);
        payment.setDateOfPayment(new MyDate(2021, 2, 29));
    }

    @Test(expected = IllegalArgumentException.class)
    public void paymentSetDateOfPaymentTest() {
        Payment payment = new Payment("Ded", new MyDate(2000, 1, 1), 100_000);
        payment.setDateOfPayment(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void paymentSetSumTest() {
        Payment payment = new Payment("Ded", new MyDate(2000, 1, 1), 100_000);
        payment.setSum(-1);
    }

    @Test
    public void paymentSetFullNameGoodTest() {
        Payment payment = new Payment("Ded", new MyDate(2000, 1, 1), 100_000);
        payment.setFullName("Hard");
    }

    @Test
    public void paymentSetDateOfPaymentGoodTest() {
        Payment payment = new Payment("Ded", new MyDate(2000, 1, 1), 100_000);
        payment.setDateOfPayment(new MyDate(2003, 5, 14));
    }

    @Test
    public void paymentSetSumGoodTest() {
        Payment payment = new Payment("Ded", new MyDate(2000, 1, 1), 100_000);
        payment.setSum(100_000_000);
    }
}
