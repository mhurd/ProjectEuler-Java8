package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
 * there are exactly 6 routes to the bottom right corner. How many such routes are there through a 20×20 grid?
 * <p>
 * https://projecteuler.net/problem=15
 */
class Problem015_LatticePaths implements EulerSolution {

    /**
     * In a grid of size m by n, we know that no matter what path we take, there will be exactly m movements to the
     * right (R) and n movements down (D). This means the pathway can be represented as a string of
     * R's and D's of length m + n. Note that for a single configuration, once we place the the R's we
     * immediately know where the D's must go (as they cannot go anywhere else). All we really have to know
     * is how many ways we can place the R's. The number of ways we can do this, mathematically, is denoted
     * with the binomial coefficient. As this problem is a perfect square (m == n) then the formula simplifies to
     * the product of i..n where (n+i)/i
     *
     * @param n - the size of the square side
     * @return the number of routes
     */
    private long countRoutes(long n) {
        return LongStream.rangeClosed(1, n).reduce(1L, (a, i) -> a = a * (n + i)/i);
    }

    public void solve() {
        assertEquals(137846528820L, countRoutes(20));
    }

}
