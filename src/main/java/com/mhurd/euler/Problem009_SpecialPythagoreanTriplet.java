package com.mhurd.euler;

import com.mhurd.euler.helpers.EulerSolution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 * <p>
 * https://projecteuler.net/problem=9
 */
class Problem009_SpecialPythagoreanTriplet implements EulerSolution {

    private List<long[]> triplets(long tripletSum) {
        long halfSum = tripletSum / 2;
        List<long[]> results = new ArrayList<>();
        for (long a = 0; a <= halfSum; a++) {
            for (long b = a + 1; b < halfSum; b++) {
                for (long c = b + 1; c < halfSum; c++) {
                    long[] triplet = new long[] {a, b, c};
                    if (isPythagoreanTriplet(triplet) && sumTriplet(triplet) == tripletSum) {
                        results.add(triplet);
                    }
                }
            }
        }
        return results;
    }

    private long sumTriplet(long[] triplet) {
        return triplet[0] + triplet[1] + triplet[2];
    }

    private boolean isPythagoreanTriplet(long[] triplet) {
        return triplet[0] * triplet[0] + triplet[1] * triplet[1] == triplet[2] * triplet[2];
    }

    public void solve() {
        List<long[]> results = triplets(1000L);
        assertTrue(results.size() == 1);
        long[] result = results.get(0);
        assertEquals(31875000L, result[0] * result[1] * result[2]);
    }

}
