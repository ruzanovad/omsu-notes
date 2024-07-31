package com.ruska112.sem4;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class MyDate {
    private int day;
    private String month;
    private int year;
    private final Map<String, Integer> monthsNum = new HashMap<String, Integer>();

    private final Map<Integer, String> months31 = new HashMap<>();

    private final Map<Integer, String> months30 = new HashMap<>();

    private final Map<String, Integer> monthsNum31 = new HashMap<>();

    private final Map<String, Integer> monthsNum30 = new HashMap<>();

    {
        months31.put(1, "january");
        months31.put(3, "march");
        months30.put(4, "april");
        months31.put(5, "may");
        months30.put(6, "june");
        months31.put(7, "july");
        months31.put(8, "august");
        months30.put(9, "september");
        months31.put(10, "october");
        months30.put(11, "november");
        months31.put(12, "december");
    }

    {
        monthsNum31.put("january", 1);
        monthsNum31.put("march", 3);
        monthsNum30.put("april", 4);
        monthsNum31.put("may", 5);
        monthsNum30.put("june", 6);
        monthsNum31.put("july", 7);
        monthsNum31.put("august", 8);
        monthsNum30.put("september", 9);
        monthsNum31.put("october", 10);
        monthsNum30.put("november", 11);
        monthsNum31.put("december", 12);
    }

    {
        monthsNum.put("january", 1);
        monthsNum.put("february", 2);
        monthsNum.put("march", 3);
        monthsNum.put("april", 4);
        monthsNum.put("may", 5);
        monthsNum.put("june", 6);
        monthsNum.put("july", 7);
        monthsNum.put("august", 8);
        monthsNum.put("september", 9);
        monthsNum.put("october", 10);
        monthsNum.put("november", 11);
        monthsNum.put("december", 12);
    }

    public MyDate() {
        day = 21;
        month = "August";
        year = 7;
    }

    public MyDate(int year, String month, int day) {
        if (year >= 1 && year <= Year.now().getValue()) {
            this.year = year;
            if (months31.containsValue(month.trim().toLowerCase())) {
                this.month = month.trim().toLowerCase();
                if (day >= 1 && day <= 31) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("MyDate constructor with String month: day error");
                }
            } else if (months30.containsValue(month.trim().toLowerCase())) {
                this.month = month.trim().toLowerCase();
                if (day >= 1 && day <= 30) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("MyDate constructor with String month: day error");
                }
            } else if (month.trim().equalsIgnoreCase("february")) {
                this.month = month.trim().toLowerCase();
                if (year % 4 == 0) {
                    if (day >= 1 && day <= 29) {
                        this.day = day;
                    } else {
                        throw new IllegalArgumentException("MyDate constructor with String month: day error");
                    }
                } else {
                    if (day >= 1 && day <= 28) {
                        this.day = day;
                    } else {
                        throw new IllegalArgumentException("MyDate constructor with String month: day error");
                    }
                }
            } else {
                throw new IllegalArgumentException("MyDate constructor with String month: month error");
            }
        } else {
            throw new IllegalArgumentException("MyDate constructor with String month: year error");
        }
    }

    public MyDate(int year, int month, int day) {
        if (year >= 1 && year <= Year.now().getValue()) {
            this.year = year;
            if (monthsNum31.containsValue(month)) {
                this.month = months31.get(month).trim().toLowerCase();
                if (day >= 1 && day <= 31) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("MyDate constructor with int month: day error");
                }
            } else if (monthsNum30.containsValue(month)) {
                this.month = months30.get(month).trim().toLowerCase();
                if (day >= 1 && day <= 30) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("MyDate constructor with int month: day error");
                }
            } else if (month == 2) {
                this.month = "february";
                if (year % 4 == 0) {
                    if (day >= 1 && day <= 29) {
                        this.day = day;
                    } else {
                        throw new IllegalArgumentException("MyDate constructor with int month: day error");
                    }
                } else {
                    if (day >= 1 && day <= 28) {
                        this.day = day;
                    } else {
                        throw new IllegalArgumentException("MyDate constructor with int month: day error");
                    }
                }
            } else {
                throw new IllegalArgumentException("MyDate constructor with int month: month error");
            }
        } else {
            throw new IllegalArgumentException("MyDate constructor with int month: year error");
        }
    }

    public void setYear(int year) {
        if (year >= 1 && year <= Year.now().getValue()) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("MyDate setYear: year illegal argument");
        }
    }

    public void setMonth(String month) {
        if (months31.containsValue(month.trim().toLowerCase())) {
            if (day >= 1 && day <= 31) {
                this.month = month.trim().toLowerCase();
            } else {
                throw new IllegalArgumentException("MyDate setMonth String: month illegal argument");
            }
        } else if (months30.containsValue(month.trim().toLowerCase())) {
            if (day >= 1 && day <= 30) {
                this.month = month.trim().toLowerCase();
            } else {
                throw new IllegalArgumentException("MyDate setMonth String: month illegal argument");
            }
        } else if (month.trim().equalsIgnoreCase("february")) {
            if (this.year % 4 == 0) {
                if (day >= 1 && day <= 29) {
                    this.month = month.trim().toLowerCase();
                } else {
                    throw new IllegalArgumentException("MyDate setMonth String: month illegal argument");
                }
            } else {
                if (day >= 1 && day <= 28) {
                    this.month = month.trim().toLowerCase();
                } else {
                    throw new IllegalArgumentException("MyDate setMonth String: month illegal argument");
                }
            }
        } else {
            throw new IllegalArgumentException("MyDate setMonth String: month illegal argument");
        }
    }

    public void setMonth(int month) {
        if (monthsNum31.containsValue(month)) {
            if (day >= 1 && day <= 31) {
                this.month = months31.get(month);
            } else {
                throw new IllegalArgumentException("MyDate setMonth int: month illegal argument");
            }
        } else if (monthsNum30.containsValue(month)) {
            if (day >= 1 && day <= 30) {
                this.month = months30.get(month);
            } else {
                throw new IllegalArgumentException("MyDate setMonth int: month illegal argument");
            }
        } else if (month == 2) {
            if (this.year % 4 == 0) {
                if (day >= 1 && day <= 29) {
                    this.month = "february";
                } else {
                    throw new IllegalArgumentException("MyDate setMonth int: month illegal argument");
                }
            } else {
                if (day >= 1 && day <= 28) {
                    this.month = "february";
                } else {
                    throw new IllegalArgumentException("MyDate setMonth int: month illegal argument");
                }
            }
        } else {
            throw new IllegalArgumentException("MyDate setMonth int: month illegal argument");
        }
    }

    public void setDay(int day) {
        if (months31.containsValue(this.month)) {
            if (day >= 1 && day <= 31) {
                this.day = day;
            } else {
                throw new IllegalArgumentException("MyDate setDay: day illegal argument");
            }
        } else if (months30.containsValue(this.month)) {
            if (day >= 1 && day <= 30) {
                this.day = day;
            } else {
                throw new IllegalArgumentException("MyDate setDay: day illegal argument");
            }
        } else if (this.month.equals("february")) {
            if (this.year % 4 == 0) {
                if (day >= 1 && day <= 29) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("MyDate setDay: day illegal argument");
                }
            } else {
                if (day >= 1 && day <= 28) {
                    this.day = day;
                } else {
                    throw new IllegalArgumentException("MyDate setDay: day illegal argument");
                }
            }
        } else {
            throw new IllegalArgumentException("MyDate setDay: day illegal argument");
        }
    }

    public int getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public int getMonthNum() {
        return monthsNum.get(this.month);
    }

    public int getYear() {
        return year;
    }

    /*
    return true if this date before param
     */
    public boolean isBefore(MyDate date) {
        boolean result = false;
        if (date != null) {
            if (this.getYear() < date.getYear()) {
                result = true;
            } else if (this.getYear() == date.getYear()) {
                if (this.getMonthNum() < date.getMonthNum()) {
                    result = true;
                } else if (this.getMonthNum() == date.getMonthNum()) {
                    if (this.getDay() < date.getDay()) {
                        result = true;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("MyDate isBefore: date == null!");
        }
        return result;
    }

    public int hashCode() {
        int result = month.hashCode();
        result += 112 * year;
        result += 112 * day;
        return result;
    }

    public boolean equals(MyDate date) {
        if (date == null) {
            throw new IllegalArgumentException("MyDate equals: date argument is null!");
        } else {
            return (this.day == date.getDay() && this.month.equalsIgnoreCase(date.getMonth()) && this.year == date.getYear());
        }
    }
}
