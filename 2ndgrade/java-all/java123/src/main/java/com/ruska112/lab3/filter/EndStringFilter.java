package com.ruska112.lab3.filter;

public class EndStringFilter implements IFilter {
    private String pattern;

    public EndStringFilter(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("EndFilter constructor: argument is null");
        }
        this.pattern = pattern;
    }

    @Override
    public boolean apply(String str) {
        if (str == null) {
            throw new IllegalArgumentException("EndFilter apply: argument is null");
        }
        return str.endsWith(pattern);
    }
}
