package com.ruska112.lab2;

import com.ruska112.sem4.MyDate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FinanceReportTest {

    FinanceReport financeReport;

    @Before
    public void setup() {
        ArrayList<Payment> payments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            payments.add(new Payment(String.format("%s%d", "ded", i), new MyDate((2000 + i), 3, 18), (100_000 + i)));
        }
        financeReport = new FinanceReport(payments, "Ruska", new MyDate(2022, 11, 6));
    }


    @Test(expected = IllegalArgumentException.class)
    public void financeReportConstructorFullNameTest() {
        FinanceReport financeReport1 = new FinanceReport(new ArrayList<Payment>(), null, new MyDate(2022, 11, 6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void financeReportConstructorMyDateTest() {
        FinanceReport financeReport1 = new FinanceReport(new ArrayList<Payment>(), "Ruska", null);
    }

    @Test
    public void financeReportGetPaymentGoodTest() {
        System.out.println(financeReport.getPayment(0).toString());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void financeReportGetPaymentTest() {
        System.out.println(financeReport.getPayment(4).toString());
    }

    @Test
    public void financeReportToStringTest() {
        System.out.println(financeReport.toString());
    }

}