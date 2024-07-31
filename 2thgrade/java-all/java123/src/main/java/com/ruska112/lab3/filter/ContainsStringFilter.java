package com.ruska112.lab3.filter;

public class ContainsStringFilter implements IFilter {
    private String pattern;

    public ContainsStringFilter(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("ContainsStringFilter constructor: argument is null");
        }
        this.pattern = pattern;
    }

    @Override
    public boolean apply(String str) {
        if (str == null) {
            throw new IllegalArgumentException("ContainsStringFilter apply: argument is null");
        }
        return str.contains(pattern);
    }
}
