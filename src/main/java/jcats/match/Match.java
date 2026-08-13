package jcats.match;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Match<T, R> {

    private final T in;

    private final R out;

    private final boolean done;

    Match(final T in, final R out) {
        this.in = in;
        this.out = out;
        this.done = false;
    }

    Match(final T in, final R out, final T case_) {
        final var cond = case_.equals(in);
        this.in = in;
        this.done = cond;
        this.out = cond ? out : null;
    }

    Match(final T in, final Supplier<R> out, final T case_) {
        final var cond = case_.equals(in);
        this.in = in;
        this.done = cond;
        this.out = cond ? out.get() : null;
    }

    Match(final T in, final R out, final Predicate<T> case_) {
        final var cond = case_.test(in);
        this.in = in;
        this.done = cond;
        this.out = cond ? out : null;
    }

    Match(final T in, final Supplier<R> out, final Predicate<T> case_) {
        final var cond = case_.test(in);
        this.in = in;
        this.done = cond;
        this.out = cond ? out.get() : null;
    }

    Match(final T in, final Function<T, R> out, final Predicate<T> case_) {
        final var cond = case_.test(in);
        this.in = in;
        this.done = cond;
        this.out = cond ? out.apply(in) : null;
    }

    <E extends RuntimeException> Match(final T in, final ExceptionSupplier<E> out, final Predicate<T> case_) {
        if (case_.test(in)) {
            throw out.get();
        }
        this(in, null);
    }

    public Match<T, R> when(final T case_, final R out) {
        return done ? this : new Match<>(in, out, case_);
    }

    public Match<T, R> when(final T case_, final Supplier<R> out) {
        return done ? this : new Match<>(in, out, case_);
    }

    public Match<T, R> when(final Predicate<T> case_, final R out) {
        return done ? this : new Match<>(in, out, case_);
    }

    public Match<T, R> when(final Predicate<T> case_, final Supplier<R> out) {
        return done ? this : new Match<>(in, out, case_);
    }

    public Match<T, R> when(final Predicate<T> case_, final Function<T, R> out) {
        return done ? this : new Match<>(in, out, case_);
    }

    public <E extends RuntimeException> Match<T, R> when(final Predicate<T> case_, final ExceptionSupplier<E> out) {
        return done ? this : new Match<>(in, out, case_);
    }

    public R orNull() {
        return out;
    }

    public R or(final R defaultValue) {
        return out == null ? defaultValue : out;
    }

    public R or(final Supplier<R> defaultValue) {
        return out == null ? defaultValue.get() : out;
    }

    public R or(final Function<T, R> defaultValue) {
        return out == null ? defaultValue.apply(in) : out;
    }

    public <E extends RuntimeException> R exceptionally(final ExceptionSupplier<E> defaultValue) {
        if (out == null) {
            throw defaultValue.get();
        }
        return out;
    }

    public Partial<R> partial() {
        return new Partial<>(out);
    }

    public Partial<R> partial(final R defaultValue) {
        return new Partial<>(defaultValue);
    }

    public Partial<R> partial(final Supplier<R> defaultValue) {
        return new Partial<>(or(defaultValue));
    }

    public Partial<R> partial(final Function<T, R> defaultValue) {
        return new Partial<>(or(defaultValue));
    }

    public <E extends RuntimeException> Partial<R> partial(final ExceptionSupplier<E> defaultValue) {
        return new Partial<>(exceptionally(defaultValue));
    }

    public <T2> Match<T2, R> pattern(final T2 value) {
        return new Match<>(value, out);
    }

    public Optional<R> toOption() {
        return Optional.ofNullable(out);
    }

    public CompletionStage<R> toCompletionStage() {
        return CompletableFuture.completedFuture(out);
    }

    public Flow.Publisher<R> toPublisher() {
        return subscriber -> {
            subscriber.onNext(out);
            subscriber.onComplete();
        };
    }

}
