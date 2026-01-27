package com.ruska112;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class LambdaRunner {

    public static <T, R> R run(Function<T, R> function, T t) {
        return function.apply(t);
    }

    public static <T, U> boolean run(BiPredicate<T, U> biPredicate, T t, U u) {
        return biPredicate.test(t, u);
    }

    public static <X, Y, Z, T> boolean run(HumansLambda<X, Y, Z, T> humansLambda, X x, Y y, Z z, T t) {
        return humansLambda.apply(x, y, z, t);
    }
}
