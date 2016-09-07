package com.mhurd.euler;

import com.mhurd.euler.helpers.LongStreamAdditions;
import com.mhurd.euler.helpers.EulerSolution;
import com.mhurd.euler.helpers.Primes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 * <p>
 * https://projecteuler.net/problem=3
 */
public class Problem3_LargestPrimeFactor implements EulerSolution {

    private long solveFor(final long targetNumber) {
        return LongStreamAdditions.wrap(Primes.primes())
                .conditionalReduce(
                        2, // start with the first prime
                        (a, b) -> determineHighestPrimeFactor(targetNumber, a, b), // keep the highest prime factor
                        l -> l < Math.sqrt(targetNumber)); // stop when the primes are > than the sqrt of the target
    }

    private long determineHighestPrimeFactor(final long targetNumber, final long accumulator, final long nextPrime) {
        return (nextPrime > accumulator // in case we do out of order processing check that the next prime is greater
                && targetNumber % nextPrime == 0) ? // has to be a factor of our target number
                    nextPrime : accumulator; // either use the new prime factor or keep the old one
    }

    @Test
    public void solve() {
        final long result = solveFor(600851475143L);
        assertEquals(6857L, result);
    }

}
