package com.ruska112.lab2;

import com.ruska112.lab3.product.Product;
import com.ruska112.sem4.MyDate;

import java.util.ArrayList;

public class FinanceReport {
    private ArrayList<Payment> paymentsArray;
    private String fullNameReporter;

    private MyDate dateOfReport;

    public int countOfPayments() {
        return paymentsArray.size();
    }

    public FinanceReport() {
        this.fullNameReporter = new String("NULL");
        this.dateOfReport = new MyDate(1970, 1, 1);
        paymentsArray.add(new Payment());
    }

    public FinanceReport(ArrayList<Payment> paymentsArray, String fullNameReporter, MyDate dateOfReport) {
        if (paymentsArray == null) {
            throw new IllegalArgumentException("FinanceReport constructor: length argument less or equals 0!");
        } else {
            if (fullNameReporter == null) {
                throw new IllegalArgumentException("FinanceReport constructor: fullNameReporter argument is null!");
            } else {
                if (dateOfReport == null) {
                    throw new IllegalArgumentException("FinanceReport constructor: dateOfReport argument is null!");
                } else {
                    this.paymentsArray = paymentsArray;
                    this.fullNameReporter = fullNameReporter;
                    this.dateOfReport = dateOfReport;
                }
            }
        }
    }

    public FinanceReport(FinanceReport financeReport) {
        this.fullNameReporter = new String(financeReport.getFullNameReporter());
        this.dateOfReport = financeReport.dateOfReport;
        for (int i = 0; i < financeReport.length(); i++) {
            Payment payment = financeReport.getPayment(i);
            paymentsArray.add(new Payment(payment.getFullName(), payment.getDateOfPayment(), payment.getSum()));
        }
    }

    public Payment getPayment(int index) {
        if (index < 0 || index >= this.countOfPayments()) {
            throw new ArrayIndexOutOfBoundsException("FinanceReport getPayment: index less than 0!");
        } else {
            return paymentsArray.get(index);
        }
    }

    public void setPayment(int index, Payment payment) {
        if (index < 0 || index >= this.countOfPayments()) {
            throw new IllegalArgumentException("FinanceReport setPayment: index is illegal");
        } else {
            if (payment == null) {
                throw new IllegalArgumentException("FinanceReport setPayment: payment argument is null!");
            } else {
                paymentsArray.set(index, payment);
            }
        }
    }

    public String getFullNameReporter() {
        return this.fullNameReporter;
    }

    public MyDate getDateOfReport() {
        return dateOfReport;
    }

    public int length() {
        return paymentsArray.size();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(1024);
        stringBuilder.append(String.format("[Автор: %s, дата: %d-%d-%d, Платежи: [\n", this.fullNameReporter, this.dateOfReport.getDay(), this.dateOfReport.getMonthNum(), this.dateOfReport.getYear()));
        for (Payment payment : paymentsArray) {
            stringBuilder.append(payment.toString());
            stringBuilder.append("\n");
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }

}
