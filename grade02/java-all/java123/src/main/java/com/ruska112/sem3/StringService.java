package com.ruska112.sem3;

public class StringService {
    public static int getCountOfStringsStartsWith(String[] array, char c) {
        int result = 0;
        String string = String.valueOf(c);
        for (String str : array) {
            if (string.equals(String.valueOf(str.charAt(0)))) {
                result++;
            }
        }
        return result;
    }

    public static int getCountOfStringsEquals(String[] array, String string) {
        int result = 0;
        for (String str : array) {
            if (string.equals(str)) {
                result++;
            }
        }
        return result;
    }

    public static int getCountOfStringsWithoutSubstring(String[] array, String string) throws IllegalArgumentException {
        int result = 0;
        if (!"".equals(string) && string != null) {
            for (String value : array) {
                if (value.length() >= string.length()){
                    for (int j = 0; j < value.length() - string.length() + 1; j++) {
                        if (!(value.startsWith(string, j))) {
                            result++;
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("String is empty");
        }
        return result;
    }

    public static String[] addNumAtStart(String[] array) throws IllegalArgumentException {
        String[] result;
        if (array != null) {
            result = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    result[i] = String.format("%d %s", (i + 1), array[i]);
                } else {
                    result[i] = String.format("%d ", (i + 1));
                }
            }
        } else {
            throw new IllegalArgumentException("StringService addNumAtStart array is empty");
        }
        return result;
    }
}
