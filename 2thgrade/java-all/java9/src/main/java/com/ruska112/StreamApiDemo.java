package com.ruska112;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class StreamApiDemo extends LambdaDemo {
    public static final UnaryOperator<List<Object>> deleteAllNull =
            list -> list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));

    public static final Function<Set<Integer>, Integer> getCountPositive =
            set -> (int) set.stream().filter(x -> x > 0).count();

    public static final UnaryOperator<List<Object>> getLastThree =
            list -> list.subList(list.size() - 3, list.size());

    public static final Function<List<Integer>, Integer> getFirstEven =
            list -> list.stream().filter(x -> (x % 2 == 0)).findFirst().orElse(null);

    public static final UnaryOperator<List<Integer>> getSquares =
            list -> list.stream().filter(Objects::nonNull).map(x -> x * x).distinct().collect(Collectors.toCollection(ArrayList::new));

    public static final UnaryOperator<List<String>> getSortAllNonEmpty =
            list -> list.stream().filter(x -> x != null && !x.equals("")).sorted(Comparator.naturalOrder()).toList();

    public static final Function<Set<String>, List<String>> getStringList =
            set -> set.stream().sorted(Comparator.reverseOrder()).toList();

    public static final Function<Set<Integer>, Integer> getSumOfSquares =
            set -> set.stream().filter(Objects::nonNull).map(x -> x * x).reduce(0, Integer::sum);

    public static final Function<Collection<Human>, Integer> getHumanMaxAge =
            col -> col.stream().map(Human::getAge).max(Integer::compare).get();

    public static final UnaryOperator<Collection<Human>> getSortHumans =
            col -> col.stream().sorted(Comparator.comparing(Human::getGender).thenComparing(Comparator.comparingInt(Human::getAge))).toList();
}
