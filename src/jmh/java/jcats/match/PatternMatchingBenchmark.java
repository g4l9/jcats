package jcats.match;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
public class PatternMatchingBenchmark {

    private static final int[][] VALUES = {
            {2, 3},
            {4, 5},
            {6, 7}
    };

    @Param({"0", "1", "2"})
    public int idx;

    @Benchmark
    public void imperative(Blackhole bh) {
        int[] v = VALUES[idx];
        bh.consume(PatternMatchingHelper.imperative(v[0], v[1]));
    }

    @Benchmark
    public void functional(Blackhole bh) {
        int[] v = VALUES[idx];
        bh.consume(PatternMatchingHelper.functional(v[0], v[1]));
    }
}