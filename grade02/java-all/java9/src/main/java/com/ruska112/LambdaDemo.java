package com.ruska112;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class LambdaDemo {
    public static final Function<String, Integer> getLength = String::length;
    public static final Function<String, Character> firstChar = string -> (string != null || string.length() == 0) ? string.charAt(0) : null;

    public static final Function<String, Boolean> isDoesNotContainsSpaces = string -> !string.contains(" ");

    public static final Function<String, Integer> getWordsCount = string -> (int) Arrays.stream(string.split(",")).count();

    public static final Function<Human, Integer> getAge = Human::getAge;

    public static final BiPredicate<Human, Human> equalsLastName = (human0, human1) -> human0.getLastName().equals(human1.getLastName());

    public static final Function<Human, String> getFullName = human -> human.getLastName() + " " + human.getFirstName() + " " + human.getPatronymic();

    public static final Function<Human, Human> incrementAge = human -> new Human(human.getLastName(), human.getFirstName(), human.getPatronymic(), human.getAge() + 1, human.getGender());

    public static final HumansLambda<Human, Human, Human, Integer> isAllYoungerThat = (human0, human1, human2, maxAge) -> human0.getAge() < maxAge && human1.getAge() < maxAge && human2.getAge() < maxAge;
}
