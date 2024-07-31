package com.ruska112.lab2;

public class RearrangeWords {
    public static String rearrangeWords(String input) {
        if (input == null) {
            throw new IllegalArgumentException("RearrangeWords rearrangeWords: illegal input argument");
        } else {
            if (input.length() != 0) {
                StringBuffer string = new StringBuffer(input);
                StringBuffer result = new StringBuffer();
                boolean flag = true;
                int i = 0, j = input.length() - 1, spaceLen = 0, wordLen = 0;
                if (string.charAt(0) == ' ') {
                    flag = false;
                }
                while (i < input.length() || j > 0) {
                    if (!flag && i <= input.length()) {
                        if (string.charAt(i) == ' ') {
                            spaceLen++;
                            if (i + 1 != input.length()) {
                                if (string.charAt(i + 1) != ' ') {
                                    result.append(string.substring(i + 1 - spaceLen, i + 1));
                                    flag = true;
                                    spaceLen = 0;
                                }
                            } else {
                                result.append(string.substring(i + 1 - spaceLen, input.length()));
                                flag = true;
                            }
                        }
                        i++;
                    }
                    if (flag && j >= 0) {
                        if (string.charAt(j) != ' ') {
                            wordLen++;
                            if (j - 1 != -1) {
                                if (string.charAt(j - 1) == ' ') {
                                    result.append(string.substring(j, j + wordLen));
                                    flag = false;
                                    wordLen = 0;
                                }
                            } else {
                                result.append(string.substring(j, j + wordLen));
                                flag = false;
                            }
                        }
                        j--;
                    }
                }
                return result.toString();
            } else {
                return input;
            }
        }
    }
}
