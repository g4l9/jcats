package jcats.match;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class PatternMatchingBenchmark {

    private int lhs;
    private int rhs;

    @Setup(Level.Trial)
    public void setUp() {
        final var random = ThreadLocalRandom.current();
        this.lhs = random.nextInt();
        this.rhs = random.nextInt();
    }

    @Benchmark
    public void imperative(Blackhole bh) {
        bh.consume(PatternMatchingHelper.imperative(lhs, rhs));
    }

    @Benchmark
    public void functional(Blackhole bh) {
        bh.consume(PatternMatchingHelper.functional(lhs, rhs));
    }
}