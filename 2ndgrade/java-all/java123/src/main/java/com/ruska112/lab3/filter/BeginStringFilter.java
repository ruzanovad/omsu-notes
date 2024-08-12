package com.ruska112.lab3.filter;

public class BeginStringFilter implements IFilter {
    private String pattern;

    public BeginStringFilter(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("BeginStringFilter constructor: argument is null");
        }
        this.pattern = pattern;
    }

    @Override
    public boolean apply(String str) {
        if (str == null) {
            throw new IllegalArgumentException("BeginStringFilter apply: argument is null");
        }
        return str.startsWith(pattern);
    }
}
