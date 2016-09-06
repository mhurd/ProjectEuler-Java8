package com.mhurd.euler.helpers;

import java.util.stream.LongStream;

@FunctionalInterface
public interface EulerSolution {

    int REPEATS = 50;

    static long avgTimeInNanos(EulerSolution solution) {
        long total = LongStream.range(0, REPEATS).reduce(0, (accumulator, i) -> {
            long start = System.nanoTime();
            solution.solve();
            long stop = System.nanoTime();
            accumulator += stop - start;
            return accumulator;
        });
        return total/ REPEATS;
    }

    void solve();

}
