package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of
 * these multiples is 23. Find the sum of all the multiples of 3 or 5 below 1000.
 * <p>
 * https://projecteuler.net/problem=1
 */
class Problem1_MultiplesOf3And5 implements EulerSolution {

    private long solve(final int to) {
        return LongStream.range(0, to).filter(i -> i % 3 == 0 || i % 5 == 0).sum();
    }

    public void solve() {
        final long result = solve(1000);
        assertEquals(233168, result);
    }

}
