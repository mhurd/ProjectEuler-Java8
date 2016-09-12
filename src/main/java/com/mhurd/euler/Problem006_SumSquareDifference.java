package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers
 * and the square of the sum is 3025 − 385 = 2640. Find the difference between the sum of the
 * squares of the first one hundred natural numbers and the square of the sum.
 * <p>
 * https://projecteuler.net/problem=6
 */
class Problem006_SumSquareDifference implements EulerSolution {

    private long sumOfTheSquare(long max) {
        return LongStream.rangeClosed(0, max)
                .map(i -> i * i)
                .sum();
    }

    private long squareOfTheSum(long max) {
        long sum = LongStream.rangeClosed(0, max).sum();
        return sum * sum;
    }

    public void solve() {
        final long sumOfTheSquare = sumOfTheSquare(100);
        final long squareOfTheSum = squareOfTheSum(100);
        final long diff = Math.abs(sumOfTheSquare - squareOfTheSum);
        assertEquals(25164150L, diff);
    }

}
