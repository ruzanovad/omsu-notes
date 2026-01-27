package com.ruska112.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplaceHexTest {

    @Test
    public void replaceAllHexToIntTestEmpty() {
        String result = ReplaceHex.replaceAllHexToInt("");
        assertEquals("", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void replaceAllHexToIntTestNull() {
        String result = ReplaceHex.replaceAllHexToInt(null);
    }

    @Test
    public void replaceAllHexToIntTest0() {
        String result = ReplaceHex.replaceAllHexToInt("Васе 0x00000010 лет");
        assertEquals("Васе 16 лет", result);
    }

    @Test
    public void replaceAllHexToIntTest1() {
        String result = ReplaceHex.replaceAllHexToInt("Васе 0x00000010 лет, а Лене - 0x00001001!");
        assertEquals("Васе 16 лет, а Лене - 4097!", result);
    }

    @Test
    public void replaceAllHexToIntTest2() {
        String result = ReplaceHex.replaceAllHexToInt("0x11111111 - 0x00000000");
        assertEquals("286331153 - 0", result);
    }

    @Test
    public void replaceAllHexToIntTest3() {
        String result = ReplaceHex.replaceAllHexToInt("АБВ0x11111111-0x00000000ABC");
        assertEquals("АБВ286331153-0ABC", result);
    }
}