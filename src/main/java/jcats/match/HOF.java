package jcats.match;

import jcats.tuple.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public final class HOF {

    public static <T> LazyMatch<T> match(final T in) {
        return new LazyMatch<>(in);
    }

    public static <T1, T2> Tuple2<T1, T2> on(final T1 t1, final T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> on(final T1 t1, final T2 t2, final T3 t3) {
        return new Tuple3<>(t1, t2, t3);
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> on(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return new Tuple4<>(t1, t2, t3, t4);
    }

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> on(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return new Tuple5<>(t1, t2, t3, t4, t5);
    }

    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> on(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
        return new Tuple6<>(t1, t2, t3, t4, t5, t6);
    }

    public static <T1, T2> Predicate<Tuple2<T1, T2>> on(final Predicate<T1> t1, final Predicate<T2> t2) {
        return tuple -> t1.test(tuple.t1()) && t2.test(tuple.t2());
    }

    public static <T1, T2, T3> Predicate<Tuple3<T1, T2, T3>> on(final Predicate<T1> t1, final Predicate<T2> t2, final Predicate<T3> t3) {
        return tuple -> t1.test(tuple.t1()) && t2.test(tuple.t2()) && t3.test(tuple.t3());
    }

    public static <T1, T2, T3, T4> Predicate<Tuple4<T1, T2, T3, T4>> on(final Predicate<T1> t1, final Predicate<T2> t2, final Predicate<T3> t3, final Predicate<T4> t4) {
        return tuple -> t1.test(tuple.t1()) && t2.test(tuple.t2()) && t3.test(tuple.t3()) && t4.test(tuple.t4());
    }

    public static <T1, T2, T3, T4, T5> Predicate<Tuple5<T1, T2, T3, T4, T5>> on(final Predicate<T1> t1, final Predicate<T2> t2, final Predicate<T3> t3, final Predicate<T4> t4, final Predicate<T5> t5) {
        return tuple -> t1.test(tuple.t1()) && t2.test(tuple.t2()) && t3.test(tuple.t3()) && t4.test(tuple.t4()) && t5.test(tuple.t5());
    }

    public static <T1, T2, T3, T4, T5, T6> Predicate<Tuple6<T1, T2, T3, T4, T5, T6>> on(final Predicate<T1> t1, final Predicate<T2> t2, final Predicate<T3> t3, final Predicate<T4> t4, final Predicate<T5> t5, final Predicate<T6> t6) {
        return tuple -> t1.test(tuple.t1()) && t2.test(tuple.t2()) && t3.test(tuple.t3()) && t4.test(tuple.t4()) && t5.test(tuple.t5()) && t6.test(tuple.t6());
    }

    public static Predicate<String> isStringEmpty() {
        return s -> s == null || s.isEmpty();
    }

    public static Predicate<String> isStringNonEmpty() {
        return isStringEmpty().negate();
    }

    public static <T> Predicate<Optional<T>> isEmpty() {
        return Optional::isEmpty;
    }

    public static <T> Predicate<Optional<T>> isPresent() {
        return Optional::isPresent;
    }

    public static <T> Predicate<T> isNull() {
        return Objects::isNull;
    }

    public static <T> Predicate<T> nonNull() {
        return Objects::nonNull;
    }

    public static <T> Predicate<T> any() {
        return _ -> true;
    }

    @SafeVarargs
    public static <T> Predicate<T> any(final T... args) {
        return any -> {
            for (final var arg : args) {
                if (any.equals(arg)) {
                    return true;
                }
            }
            return false;
        };
    }

    public static <T> Predicate<T> is(final T value) {
        return value::equals;
    }

    public static <T> Predicate<T> in(final Collection<T> collection) {
        return collection::contains;
    }

    public static <T> Predicate<T> in(final Set<T> set) {
        return set::contains;
    }

    public static <T> Predicate<T> type(final Class<? extends T> t) {
        return HOF.<T>nonNull().and(i -> i.getClass().equals(t));
    }

    private HOF() {
        throw new AssertionError();
    }
}
