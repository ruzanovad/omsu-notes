package com.ruska112.lab2;

public class ReplaceHex {
    public static String replaceAllHexToInt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("ReplaceHex replaceAllHexToInt: illegal input param");
        } else {
            StringBuffer string = new StringBuffer(input);
            int i = 0, hexStart;
            while (i < input.length()) {
                hexStart = string.indexOf("0x");
                if (hexStart == -1) {
                    break;
                }
                String hex = string.substring(hexStart + 2, hexStart + 10);
                string.replace(hexStart, hexStart + 10, String.valueOf(Long.valueOf(hex, 16)));
                i++;
            }
            return string.toString();
        }
    }
}
