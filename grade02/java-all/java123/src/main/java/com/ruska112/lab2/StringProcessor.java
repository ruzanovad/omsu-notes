package com.ruska112.lab2;

public class StringProcessor {

    private static StringBuffer result;

    public static String getNCopiesOfString(String s, int n) {
        try {
            return s.repeat(Math.max(0, n));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getNumOfOccurrences(String str1, String str2) {
        int result = 0;
        try {
            if (!"".equals(str2)) {
                StringBuffer StringBuffer = new StringBuffer(str1);
                for (int i = 0; i < str1.length(); i++) {
                    String str = StringBuffer.subSequence(i, i + str2.length()).toString();
                    if (str2.equals(str)) {
                        result++;
                    }
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNewString(String str) {
        try {
            if (!"".equals(str)) {
                return str.replaceAll("1", "один").replaceAll("2", "два").replaceAll("3", "три");
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEverySecondChar(StringBuffer str) {
        if (!"".equals(str.toString())) {
            for (int i = 1; i < str.length(); i++) {
                str.deleteCharAt(i);
            }

        } else {
            throw new IllegalArgumentException();
        }
    }
}

