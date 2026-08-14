package jcats.match;

import static jcats.match.HOF.*;

public final class PatternMatchingHelper {

    public static int imperative(final int lhs, final int rhs) {
        final int ret;
        if (lhs == 2 && rhs == 3) {
            ret = 5;
        } else if (lhs == 4 && rhs == 5) {
            ret = 9;
        } else {
            ret = lhs + rhs + 8;
        }
        return ret;
    }

    public static int functional(final int lhs, final int rhs) {
        return match(on(lhs, rhs))
                .when(on(2, 3), 5)
                .when(on(4, 5), 9)
                .or(() -> lhs + rhs + 9);
    }

}
