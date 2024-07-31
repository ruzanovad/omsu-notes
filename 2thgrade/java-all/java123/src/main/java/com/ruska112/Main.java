package com.ruska112;

import java.math.BigInteger;

public class Main {
    public static String replaceAllHexToInt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("ReplaceHex replaceAllHexToInt: illegal input param");
        } else {
            StringBuffer string = new StringBuffer(input);
            boolean flag = false;
            int j = 0, hexStart = 0, hexLength = 0;
            hexStart = string.indexOf("0x");
            j = hexStart;
            while (hexStart != -1 && j < string.length()) {
                if (j + 1 != string.length()) {
                    if (string.charAt(j + 1) != ' ') {
                        hexLength++;
                    } else {
                        flag = true;
                    }
                } else {
                    String hex = string.substring(hexStart + 2, string.length() - hexLength);
                    string.replace(hexStart, string.length()-1, String.valueOf(Long.valueOf(hex, 16)));
                }
                if (flag) {
                    String hex = string.substring(hexStart + 2, hexStart + hexLength + 1);
                    string.replace(hexStart, hexStart + hexLength + 1, String.valueOf(Long.valueOf(hex, 16)));
                    hexLength = 0;
                    hexStart = string.indexOf("0x");
                    flag = false;
                }
                j++;
            }

            return string.toString();
        }
    }

    public static void main(String[] args) {

        String str = new String("10000010000010");

        System.out.println(replaceAllHexToInt("0x01 0x10000101"));
    }
}
