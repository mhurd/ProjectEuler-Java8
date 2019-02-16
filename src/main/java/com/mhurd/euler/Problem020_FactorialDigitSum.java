package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.math.BigInteger;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * Find the sum of the digits in the number 100!
 * <p>
 * https://projecteuler.net/problem=20
 */
class Problem020_FactorialDigitSum implements EulerSolution {

    public void solve() {
        BigInteger factorial = LongStream.rangeClosed(1, 100)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, (a, l) -> l.multiply(a));
        int result = String.valueOf(factorial).chars()
                .mapToObj(c -> Integer.valueOf(String.valueOf((char) c)))
                .reduce(0, (a, l) -> a + l);
        assertEquals(648, result);
    }

}
