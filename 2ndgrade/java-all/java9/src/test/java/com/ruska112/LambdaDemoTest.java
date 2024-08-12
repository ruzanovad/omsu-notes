package com.ruska112;

import org.junit.Test;

import java.util.Optional;

import static com.ruska112.LambdaDemo.*;
import static com.ruska112.LambdaRunner.*;
import static org.junit.Assert.*;

public class LambdaDemoTest {
    @Test
    public void getLengthTest0() {
        assertEquals(Optional.of(2), Optional.of(run(getLength, "ab")));
    }

    @Test
    public void firstCharTest0() {
        assertEquals(Optional.of('a'), Optional.of(run(firstChar, "ab")));
    }

    @Test
    public void isDoesNotContainsSpacesTest0() {
        assertEquals(Optional.of(true), Optional.of(run(isDoesNotContainsSpaces, "ab")));
    }

    @Test
    public void isDoesNotContainsSpacesTest1() {
        assertEquals(Optional.of(false), Optional.of(run(isDoesNotContainsSpaces, "a b")));
    }

    @Test
    public void getWordsCountTest0() {
        assertEquals(Optional.of(4), Optional.of(run(getWordsCount, "ab,abc,qw,ert")));
    }

    @Test
    public void getAgeTest0() {
        assertEquals(Optional.of(112), Optional.of(run(getAge, new Human("a", "b", "c", 112, Gender.Male))));
    }

    @Test
    public void getAgeTest1() {
        assertEquals(Optional.of(22), Optional.of(run(getAge, new Student("a", "b", "c", 22, Gender.Male, "x", "y", "z"))));
    }

    @Test
    public void equalsLastNameTest0() {
        assertTrue(run(equalsLastName, new Human("a", "b", "c", 112, Gender.Male), new Human("a", "b", "c", 112, Gender.Male)));
    }

    @Test
    public void equalsLastNameTest1() {
        assertTrue(run(equalsLastName, new Student("a", "b", "c", 112, Gender.Male, "x", "y", "z"), new Human("a", "b", "c", 112, Gender.Male)));
    }

    @Test
    public void equalsLastNameTest2() {
        assertFalse(run(equalsLastName, new Human("Asap", "b", "c", 20, Gender.Male), new Human("Qwerty", "b", "c", 112, Gender.Male)));
    }

    @Test
    public void getFullNameTest0() {
        assertEquals("a b c", run(getFullName, new Human("a", "b", "c", 112, Gender.Male)));
    }

    @Test
    public void getFullNameTest1() {
        assertEquals("x y z", run(getFullName, new Student("x", "y", "z", 112, Gender.Male, "f", "d", "c")));
    }

    @Test
    public void incrementAgeTest0() {
        assertEquals(new Human("a", "b", "c", 113, Gender.Male), run(incrementAge, new Human("a", "b", "c", 112, Gender.Male)));
    }

    @Test
    public void isAllYoungerThatTest0() {
        Human human0 = new Human("a", "b", "c", 19, Gender.Male);
        Human human1 = new Human("a", "b", "c", 20, Gender.Male);
        Human human2 = new Human("a", "b", "c", 33, Gender.Male);
        assertTrue(run(isAllYoungerThat, human0, human1, human2, 54));
    }

    @Test
    public void isAllYoungerThatTest1() {
        Human human0 = new Human("a", "b", "c", 19, Gender.Male);
        Human human1 = new Human("a", "b", "c", 20, Gender.Male);
        Human human2 = new Human("a", "b", "c", 33, Gender.Male);
        assertFalse(run(isAllYoungerThat, human0, human1, human2, 24));
    }
}
