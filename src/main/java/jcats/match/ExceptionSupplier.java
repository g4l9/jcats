package jcats.match;

@FunctionalInterface
public interface ExceptionSupplier<E extends RuntimeException> {
    E get();
}
