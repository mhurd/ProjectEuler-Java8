package com.mhurd.euler;

import java.util.stream.IntStream;

interface TimedEulerSolution extends EulerSolution {

    int REPEATS = 500;

    default long avgTimeInNanos(EulerSolution solution) {
        long total = IntStream.range(0, REPEATS).reduce(0, (accumulator, i) -> {
            long start = System.nanoTime();
            solution.solve();
            long stop = System.nanoTime();
            accumulator += stop - start;
            return accumulator;
        });
        return total/ REPEATS;
    }

}
