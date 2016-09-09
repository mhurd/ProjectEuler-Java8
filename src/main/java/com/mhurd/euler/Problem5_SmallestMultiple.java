package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.OptionalLong;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without
 * any remainder. What is the smallest positive number that is evenly divisible by all of the
 * numbers from 1 to 20?
 * <p>
 * https://projecteuler.net/problem=5
 */
class Problem5_SmallestMultiple implements EulerSolution {

    private boolean isDivisibleByAll(final long number, final LongStream range) {
        return range.allMatch(l -> number % l == 0);
    }

    public void solve() {
        final OptionalLong result =
                LongStream.iterate(1, l -> l + 1)
                        .filter(l -> isDivisibleByAll(l, LongStream.rangeClosed(1, 20)))
                        .findFirst();
        assertTrue(result.isPresent());
        assertEquals(232792560L, result.getAsLong());
    }

}
