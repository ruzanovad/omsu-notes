package com.ruska112;

import java.util.Comparator;

public class HumanComparator implements Comparator<Human> {
    @Override
    public int compare(Human human, Human t1) {
        if (human == null) {
            throw new IllegalArgumentException();
        }
        if (t1 == null) {
            throw new IllegalArgumentException();
        }
        int i = human.getSurname().compareToIgnoreCase(t1.getSurname());
        if (i != 0) {
            return i;
        }
        return human.getName().compareToIgnoreCase(t1.getName());
    }
}
