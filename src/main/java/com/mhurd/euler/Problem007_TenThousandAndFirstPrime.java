package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;
import com.mhurd.euler.helpers.Primes;

import java.util.OptionalLong;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th
 * prime is 13. What is the 10 001st prime number?
 * <p>
 * https://projecteuler.net/problem=7
 */
class Problem007_TenThousandAndFirstPrime implements EulerSolution {

    public void solve() {
        OptionalLong result = Primes.stream().skip(10000L).findAny();
        assertTrue(result.isPresent());
        assertEquals(104743L, result.getAsLong());
    }

}
