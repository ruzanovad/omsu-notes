package com.ruska112.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class RearrangeWordsTest {

    @Test
    public void rearrangeWordsTestEmpty() {
        String result = RearrangeWords.rearrangeWords("");
        assertEquals("", result);
    }

    @Test
    public void rearrangeWordsTest0() {
        String result = RearrangeWords.rearrangeWords(" abc ");
        assertEquals(" abc ", result);
    }

    @Test
    public void rearrangeWordsTest00() {
        String result = RearrangeWords.rearrangeWords(" a b c ");
        assertEquals(" c b a ", result);
    }

    @Test
    public void rearrangeWordsTest01() {
        String result = RearrangeWords.rearrangeWords(" a b c d ");
        assertEquals(" d c b a ", result);
    }

    @Test
    public void rearrangeWordsTest1() {
        String result = RearrangeWords.rearrangeWords("abc");
        assertEquals("abc", result);
    }

    @Test
    public void rearrangeWordsTest10() {
        String result = RearrangeWords.rearrangeWords("a b c");
        assertEquals("c b a", result);
    }

    @Test
    public void rearrangeWordsTest11() {
        String result = RearrangeWords.rearrangeWords("a b c d");
        assertEquals("d c b a", result);
    }

    @Test
    public void rearrangeWordsTest2() {
        String result = RearrangeWords.rearrangeWords(" ab cd ");
        assertEquals(" cd ab ", result);
    }

    @Test
    public void rearrangeWordsTest20() {
        String result = RearrangeWords.rearrangeWords(" abc def ghi ");
        assertEquals(" ghi def abc ", result);
    }

    @Test
    public void rearrangeWordsTest3() {
        String result = RearrangeWords.rearrangeWords(" ab cd");
        assertEquals(" cd ab", result);
    }

    @Test
    public void rearrangeWordsTest30() {
        String result = RearrangeWords.rearrangeWords(" abc def ghi");
        assertEquals(" ghi def abc", result);
    }

    @Test
    public void rearrangeWordsTest4() {
        String result = RearrangeWords.rearrangeWords("ab cd ");
        assertEquals("cd ab ", result);
    }

    @Test
    public void rearrangeWordsTest40() {
        String result = RearrangeWords.rearrangeWords("abc def ghi ");
        assertEquals("ghi def abc ", result);
    }

    @Test
    public void rearrangeWordsTest5() {
        String result = RearrangeWords.rearrangeWords("ab cd");
        assertEquals("cd ab", result);
    }

    @Test
    public void rearrangeWordsTest50() {
        String result = RearrangeWords.rearrangeWords("abc def ghi");
        assertEquals("ghi def abc", result);
    }

    @Test
    public void rearrangeWordsHardTest() {
        String result = RearrangeWords.rearrangeWords("String      strin     stri    str   st  s ");
        assertEquals("s      st     str    stri   strin  String ", result);
    }

    @Test
    public void rearrangeWordsExtraHardTest() {
        String result = RearrangeWords.rearrangeWords("Roses are   red, violets  -  bLUe");
        assertEquals("bLUe -   violets red,  are  Roses", result);
    }
}