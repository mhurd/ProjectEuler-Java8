package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;
import com.mhurd.euler.helpers.LongStreamAdditions;
import org.junit.Test;

import java.util.Optional;
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
public class Problem4_SmallestMultiple implements EulerSolution {

    private boolean isDivisibleByAll(final long number, final LongStream range) {
        return range.allMatch(l -> number % l == 0);
    }

    @Test
    public void solve() {
        final LongStreamAdditions stream =
                LongStreamAdditions.wrap(LongStream.iterate(1, l -> l + 1));
        final Optional<Long> result =
                stream.findFirst(l -> isDivisibleByAll(l, LongStream.rangeClosed(1, 20)));
        assertTrue(result.isPresent());
        assertEquals(Long.valueOf(232792560L), result.get());
    }

}
