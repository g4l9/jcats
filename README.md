# jcats

PoC FP concepts implemented in Java

## Tuples

```java
import jcats.tuple.Tuple2;

import java.util.Map;

import static jcats.match.HOF.is;
import static jcats.tuple.Tuple2.toMapTuple2;
import static jcats.tuple.Tuple2.when2;

public class Test {
    void test() {
        // make tuple
        final Tuple2<String, Integer> tuple2 = Tuple2.of("test", 42);

        // swap values
        final Tuple2<Integer, String> tuple2Swap = tuple2.swap();

        // tuple from entry
        final Map<String, Integer> map = Map.of("test", 42);
        final Map.Entry<String, Integer> entry = map.entrySet().iterator().next();
        final Tuple2<String, Integer> tuple2From = Tuple2.from(entry);
        
        // filter and collect 
        map.entrySet()
                .stream()
                .map(Tuple2::from)
                .filter(when2(is(42)))
                .collect(toMapTuple2());
    }
}

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
