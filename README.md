# jcats

## Example

```java
import static jcats.match.HOF.*;

public class Test {
    public static String test(final String lhs, final String rhs) {
        return match(on(lhs, rhs))
                .when(on("test1", "test2"), "value1")
                .when(on("jojo", "jojo2"), "value2")
                .or("default_value");
    }
}

```