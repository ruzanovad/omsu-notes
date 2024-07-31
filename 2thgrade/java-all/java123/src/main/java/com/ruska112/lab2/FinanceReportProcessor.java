package com.ruska112.lab2;

import com.ruska112.sem4.MyDate;

import java.util.ArrayList;

public class FinanceReportProcessor {
    public static FinanceReport getPaymentsFromChar(FinanceReport financeReport, char c) {
        if (financeReport == null) {
            throw new IllegalArgumentException("FinanceReportProcessor getAllPaymentsFromChar: financeReport is null");
        } else {
            int j = 0;
            ArrayList<Payment> my = new ArrayList<>();
            for (int i = 0; i < financeReport.length(); i++) {
                if (financeReport.getPayment(i).getFullName().toLowerCase().charAt(0) == c) {
                    my.add(financeReport.getPayment(i));
                }
            }
            FinanceReport result = new FinanceReport(my, financeReport.getFullNameReporter(), financeReport.getDateOfReport());
            return result;
        }
    }

    public static FinanceReport getPaymentsSumLessThan(FinanceReport financeReport, int sum) {
        if (financeReport == null) {
            throw new IllegalArgumentException("FinanceReportProcessor getAllPaymentsFromChar: financeReport is null");
        } else {
            ArrayList<Payment> my = new ArrayList<>();
            for (int i = 0; i < financeReport.length(); i++) {
                if (financeReport.getPayment(i).getSum() < sum) {
                    my.add(financeReport.getPayment(i));
                }
            }
            FinanceReport result = new FinanceReport(my, financeReport.getFullNameReporter(), financeReport.getDateOfReport());
            return result;
        }
    }

    public static int getSumFromDate(FinanceReport financeReport, String date) {
        if (financeReport == null) {
            throw new IllegalArgumentException("FinanceReportProcessor getAllPaymentsFromChar: financeReport is null");
        } else {
            String[] dateArray = date.split("\\.");
            MyDate myDate = new MyDate((2000 + Integer.parseInt(dateArray[2].toLowerCase())), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
            int sum = 0;
            for (int i = 0; i < financeReport.length(); i++) {
                Payment payment = financeReport.getPayment(i);
                if (payment.getDateOfPayment().equals(myDate)) {
                    sum += payment.getSum();
                }
            }
            return sum;
        }
    }

    public static ArrayList<String> getMonthsWithoutPayments(FinanceReport financeReport, int year) {
        if (financeReport == null) {
            throw new IllegalArgumentException("FinanceReportProcessor getAllPaymentsFromChar: financeReport is null");
        } else {
            ArrayList<String> months = new ArrayList<>();

            {
                months.add(0, "january");
                months.add(1, "february");
                months.add(2, "march");
                months.add(3, "april");
                months.add(4, "may");
                months.add(5, "june");
                months.add(6, "july");
                months.add(7, "august");
                months.add(8, "september");
                months.add(9, "october");
                months.add(10, "november");
                months.add(11, "december");
            }

            for (int i = 0; i < financeReport.length(); i++) {
                if (financeReport.getPayment(i).getDateOfPayment().getYear() == year) {
                    MyDate paymentDate = financeReport.getPayment(i).getDateOfPayment();
                    months.remove(paymentDate.getMonth());
                }
            }
            return months;
        }
    }
}
