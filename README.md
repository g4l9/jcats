# jcats

This project contains PoC FP concepts implemented in Java

## Tuples

```java
// make tuple
final Tuple2<String, Integer> tuple2 = Tuple2.of("test", 42);

// swap values
final Tuple2<Integer, String> tuple2Swap = tuple2.swap();

// tuple from entry
final Map<String, Integer> map = Map.of("test", 42);
final Map.Entry<String, Integer> entry = map.entrySet().iterator().next();
final Tuple2<String, Integer> tuple2From = Tuple2.from(entry);
```

## Currying

```java
public static int sum(final int lhs, final int rhs) {
    return lhs + rhs;
}

public static List<Integer> test(final int lhs) {
    final var sum = currying(Test::sum);
    return Stream.of(1, 2, 3)
            .map(sum.apply(lhs))
            .toList();
}
```

## Pattern Matching

```java
public static String test(final String lhs, final String rhs) {
    return match(on(lhs, rhs))
            .when(on("test1", "test2"), "value1")
            .when(on("jojo", "jojo2"), "value2")
            .or("default_value");
}
```