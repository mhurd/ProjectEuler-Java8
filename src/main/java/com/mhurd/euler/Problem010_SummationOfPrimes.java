package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;
import com.mhurd.euler.helpers.LongStreamAdditions;
import com.mhurd.euler.helpers.Primes;

import static org.junit.Assert.assertEquals;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 * <p>
 * https://projecteuler.net/problem=10
 */
class Problem010_SummationOfPrimes implements EulerSolution {

    private long sumOfAllPrimesLessThan(long max) {
        return LongStreamAdditions.wrap(Primes.stream())
                .conditionalReduce(
                        0,
                        (a, b) -> a + b,
                        l -> l < max);
    }

    public void solve() {
        assertEquals(142913828922L, sumOfAllPrimesLessThan(2000000L));
    }

}
