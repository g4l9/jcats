package jcats.tuple;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public record Tuple2<T1, T2>(T1 t1, T2 t2) {

    public static <T1, T2> Tuple2<T1, T2> of(final T1 t1, final T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    public static <T1, T2> Tuple2<T1, T2> from(final Map.Entry<T1, T2> entry) {
        return new Tuple2<>(entry.getKey(), entry.getValue());
    }

    public static <T1, T2> Predicate<Tuple2<T1, T2>> when1(final Predicate<T1> p) {
        return t -> p.test(t.t1);
    }

    public static <T1, T2> Predicate<Tuple2<T1, T2>> when2(final Predicate<T2> p) {
        return t -> p.test(t.t2);
    }

    public static <T1, T2> Collector<Tuple2<T1, T2>, ?, Map<T1, T2>> toMapTuple2() {
        return Collectors.toMap(Tuple2::t1, Tuple2::t2);
    }

    public Tuple2<T2, T1> swap() {
        return of(t2, t1);
    }
}