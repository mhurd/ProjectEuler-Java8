package com.mhurd.euler;

import org.junit.Test;

import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of
 * these multiples is 23. Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * https://projecteuler.net/problem=1
 */
public class Problem1_MultiplesOf3And5 implements TimedEulerSolution {

    private long solve(int to) {
        return LongStream.range(0, to).filter(i -> i % 3 ==  0 || i % 5 == 0).sum();
    }

    @Test
    public void solve() {
        long result = solve(1000);
        assertEquals(233168, result);
    }

}
