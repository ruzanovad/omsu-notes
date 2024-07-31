package com.ruska112.lab2;

import com.ruska112.sem4.MyDate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FinanceReportProcessorTest {

    FinanceReport financeReport;

    @Before
    public void setup() {
        ArrayList<Payment> payments = new ArrayList<Payment>();
        for (int i = 0; i < 3; i++) {
            payments.add(i, new Payment(String.format("%s%d", "ded", i), new MyDate((2000), 3+i, 18), (100_00 + i)));
        }
        payments.add(3, new Payment("Android", new MyDate(2022, 2, 24), 101_000_00));
        payments.add(4, new Payment("alock", new MyDate(2011, 3, 18), 112_00));

        financeReport = new FinanceReport(payments, "Ruska", new MyDate(2022, 11, 6));
    }

    @Test
    public void getPaymentsFromCharTest() {
        String str = FinanceReportProcessor.getPaymentsFromChar(financeReport, 'a').toString();
        System.out.println("getPaymentsFromChar\n" + str + "\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPaymentsFromCharBadTest() {
        FinanceReportProcessor.getPaymentsFromChar(null, 'a');
    }

    @Test
    public void getPaymentsFromCharEmptyTest() {
        String str = FinanceReportProcessor.getPaymentsFromChar(financeReport, 'z').toString();
        System.out.println("getPaymentsFromCharEmptyTest\n" + str + "\n");
    }

    @Test
    public void getPaymentsSumLessThanTest() {
        String str = FinanceReportProcessor.getPaymentsSumLessThan(financeReport, 1000_00).toString();
        System.out.println("getPaymentsSumLessThan\n" + str + "\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPaymentsSumLessThanBadTest() {
        FinanceReportProcessor.getPaymentsSumLessThan(null, 1000);
    }

    @Test
    public void getPaymentsSumLessThanEmptyTest() {
        String str = FinanceReportProcessor.getPaymentsSumLessThan(financeReport, 10).toString();
        System.out.println("getPaymentsSumLessThanEmptyTest\n" + str + "\n");
    }

    @Test
    public void getSumFromDateTest(){
        int sum = FinanceReportProcessor.getSumFromDate(financeReport, new String("18.03.00"));
        System.out.println("getSumFromDateTest\n" + sum + "\n");
    }

    @Test
    public void getMonthsWithoutPayments() {
        ArrayList<String> months = FinanceReportProcessor.getMonthsWithoutPayments(financeReport, 2000);
        System.out.println(months.toString());
    }
}