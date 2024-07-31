package com.ruska112.sem4;

import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

public class HumanService {
    public static ArrayList<Human> getAllAdults(ArrayList<Human> people) {
        if (people.isEmpty()) {
            throw new IllegalArgumentException("HumanService getAllAdults: people parameter is empty!");
        } else {
            ArrayList<Human> adults = new ArrayList<>();
            MyDate adultDate = new MyDate(Year.now().getValue() - 18, YearMonth.now().getMonthValue(), MonthDay.now().getDayOfMonth());
            for (Human human : people) {
                if (human.getBirthday().isBefore(adultDate)) {
                    adults.add(human);
                }
            }
            return adults;
        }
    }

    public static ArrayList<Integer> getAges(ArrayList<Human> people) {
        if (people.isEmpty()) {
            throw new IllegalArgumentException("HumanService getAges: people param is empty!");
        } else {
            ArrayList<Integer> agesArrayList = new ArrayList<>();
            MyDate nowDate = new MyDate(Year.now().getValue(), YearMonth.now().getMonthValue(), MonthDay.now().getDayOfMonth());
            for (Human human : people) {
                if (human.getBirthday().isBefore(nowDate)) {
                    agesArrayList.add(nowDate.getYear() - human.getBirthday().getYear());
                }
            }
            return agesArrayList;
        }
    }
}
