package jcats.match;

import jcats.tuple.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class HOFTest {

    @Test
    void on() {
        assertEquals(new Tuple2<>("1", 2), HOF.on("1", 2));
        assertEquals(new Tuple3<>("1", 2, 3), HOF.on("1", 2, 3));
        assertEquals(new Tuple4<>("1", 2, 3, 4), HOF.on("1", 2, 3, 4));
        assertEquals(new Tuple5<>("1", 2, 3, 4, 5), HOF.on("1", 2, 3, 4, 5));
        assertEquals(new Tuple6<>("1", 2, 3, 4, 5, 6), HOF.on("1", 2, 3, 4, 5, 6));
    }

    @Test
    void testOn8() {
    }

    @Test
    void isStringEmpty() {
        final var stringEmpty = HOF.isStringEmpty();
        assertTrue(stringEmpty.test(null));
        assertTrue(stringEmpty.test(""));
        assertFalse(stringEmpty.test("a"));
        assertFalse(stringEmpty.test("as"));
    }

    @Test
    void isStringNonEmpty() {
        final var isStringNonEmpty = HOF.isStringNonEmpty();
        assertFalse(isStringNonEmpty.test(null));
        assertFalse(isStringNonEmpty.test(""));
        assertTrue(isStringNonEmpty.test("a"));
        assertTrue(isStringNonEmpty.test("as"));
    }

    @Test
    void isEmpty() {
        final var isEmpty = HOF.<String>isEmpty();
        assertThrows(NullPointerException.class, () -> isEmpty.test(null));
        assertTrue(isEmpty.test(Optional.empty()));
        assertFalse(isEmpty.test(Optional.of("")));
    }

    @Test
    void isPresent() {
        final var isPresent = HOF.<String>isPresent();
        assertThrows(NullPointerException.class, () -> isPresent.test(null));
        assertFalse(isPresent.test(Optional.empty()));
        assertTrue(isPresent.test(Optional.of("")));
    }

    @Test
    void isNull() {
        final var isNull = HOF.<String>isNull();
        assertTrue(isNull.test(null));
        assertFalse(isNull.test(""));
    }

    @Test
    void nonNull() {
        final var nonNull = HOF.<String>nonNull();
        assertFalse(nonNull.test(null));
        assertTrue(nonNull.test(""));
    }

    @Test
    void any() {
        final var any = HOF.any();
        assertTrue(any.test(""));
        assertTrue(any.test(null));
        assertTrue(any.test(Integer.MAX_VALUE));
        assertTrue(any.test(Long.MAX_VALUE));
        assertTrue(any.test(new Object()));
    }

    @Test
    void testAny() {
        final var obj = new Object();
        final var any = HOF.any("", obj, Integer.MAX_VALUE, Long.MAX_VALUE, obj);

        assertTrue(any.test(""));
        assertTrue(any.test(Integer.MAX_VALUE));
        assertTrue(any.test(Long.MAX_VALUE));
        assertTrue(any.test(obj));
        assertFalse(any.test(new Object()));

        assertThrows(NullPointerException.class, () -> any.test(null));
    }

    @Test
    void is() {
        assertTrue(HOF.is("2").test("2"));
        assertTrue(HOF.is(2).test(2));
        assertFalse(HOF.is(2).test(3));
        assertFalse(HOF.is("2").test("3"));
    }

    @Test
    void in() {
        final var in = HOF.in(List.of(2, 3, 3, 2));
        assertTrue(in.test(2));
        assertTrue(in.test(3));
        assertFalse(in.test(4));
        assertFalse(in.test(-1));
    }

    @Test
    void testIn() {
        final var in = HOF.in(Set.of(2, 3));
        assertTrue(in.test(2));
        assertTrue(in.test(3));
        assertFalse(in.test(4));
        assertFalse(in.test(-1));
    }

    @Test
    void currying() {
        final BiFunction<String, Integer, Integer> function = (lhs, rhs) -> Integer.parseInt(lhs) + rhs;
        final var currying = HOF.currying(function);
        assertEquals(7, currying.apply("3").apply(4));
    }

    @Test
    void uncurrying() {
        final Function<String, Function<Integer, Integer>> function = lhs -> rhs -> Integer.parseInt(lhs) + rhs;
        final var uncurrying = HOF.uncurrying(function);
        assertEquals(7, uncurrying.apply("4", 3));
    }
}