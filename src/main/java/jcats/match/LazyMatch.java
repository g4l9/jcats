package jcats.match;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class LazyMatch<T> {

    private final T in;

    LazyMatch(final T in) {
        this.in = in;
    }

    public <R> Match<T, R> when(final T case_, final R out) {
        return new Match<>(case_, out);
    }

    public <R> Match<T, R> when(final T case_, final Supplier<R> out) {
        return new Match<>(in, out, case_);
    }

    public <R> Match<T, R> when(final Predicate<T> case_, final R out) {
        return new Match<>(in, out, case_);
    }

    public <R> Match<T, R> when(final Predicate<T> case_, final Supplier<R> out) {
        return new Match<>(in, out, case_);
    }

    public <R> Match<T, R> when(final Predicate<T> case_, final Function<T, R> out) {
        return new Match<>(in, out, case_);
    }

    public <E extends RuntimeException> Match<T, E> when(final Predicate<T> case_, final ExceptionSupplier<E> out) {
        return new Match<>(in, out, case_);
    }

}
