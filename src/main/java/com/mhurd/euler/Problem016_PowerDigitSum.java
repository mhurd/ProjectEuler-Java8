package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 * <p>
 * https://projecteuler.net/problem=16
 */
class Problem016_PowerDigitSum implements EulerSolution {

    private long sumDigitsOfNToThePowerOf(long n, int exponent) {
        return BigInteger.valueOf(n).pow(exponent)
                .toString()
                .chars()
                .mapToObj(i -> (char)i)
                .mapToLong(c -> Long.valueOf(String.valueOf(c)))
                .reduce(0, (a, i) -> a + i);
    }

    public void solve() {
        assertEquals(1366L, sumDigitsOfNToThePowerOf(2, 1000));
    }

}
