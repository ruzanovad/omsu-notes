package com.ruska112.lab2;

import com.ruska112.sem4.MyDate;

public class Payment {
    private String fullName;

    private int sum;

    private MyDate dateOfPayment;

    public void setFullName(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("Payment setFullName: argument is null!");
        } else {
            this.fullName = fullName;
        }
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setDateOfPayment(MyDate dateOfPayment) {
        if (dateOfPayment == null) {
            throw new IllegalArgumentException("Payment setDateOfPayment: argument is null!");
        } else {
            this.dateOfPayment = dateOfPayment;
        }
    }

    public MyDate getDateOfPayment() {
        return this.dateOfPayment;
    }

    public void setSum(int sum) {
        if (sum < 0) {
            throw new IllegalArgumentException("Payment setSum: argument less than 0!");
        } else {
            this.sum = sum;
        }
    }

    public int getSum() {
        return this.sum;
    }

    public Payment() {
        this.fullName = "Ded";
        this.sum = 14052003;
        this.dateOfPayment = new MyDate(2003, 6, 8);
    }

    public Payment(String fullName, MyDate dateOfPayment, int sum) {
        if (fullName == null) {
            throw new IllegalArgumentException("Payment constructor: fullName argument is null!");
        } else {
            if (dateOfPayment == null) {
                throw new IllegalArgumentException("Payment constructor: dateOfPayment argument is null!");
            } else {
                if (sum < 0) {
                    throw new IllegalArgumentException("Payment constructor: sum less than 0!");
                } else {
                    this.fullName = fullName;
                    this.dateOfPayment = dateOfPayment;
                    this.sum = sum;
                }
            }
        }
    }

    public int hashCode() {
        int result = fullName.hashCode();
        result += sum;
        result += dateOfPayment.hashCode();
        return result;
    }

    public boolean equals(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment equals: payment argument is null!");
        } else {
            return (this.fullName.equals(payment.getFullName()) && this.dateOfPayment.equals(payment.getDateOfPayment()) && this.sum == payment.getSum());
        }
    }

    public String toString() {
        return String.format("ФИО: %s, Дата: %d.%d.%d, Сумма: %d руб. %d коп.", this.getFullName(), this.getDateOfPayment().getDay(), this.dateOfPayment.getMonthNum(), this.getDateOfPayment().getYear(), (this.getSum() / 100), (this.getSum() % 100));
    }
}
