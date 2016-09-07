package com.mhurd.euler.helpers;

import java.util.stream.LongStream;

@FunctionalInterface
public interface EulerSolution {

    static long avgTimeInNanos(final EulerSolution solution, final int repeats) {
        long total = LongStream.range(0, repeats).reduce(0, (accumulator, i) -> {
            final long start = System.nanoTime();
            solution.solve();
            final long stop = System.nanoTime();
            accumulator += stop - start;
            return accumulator;
        });
        return total/ repeats;
    }

    void solve();

}
