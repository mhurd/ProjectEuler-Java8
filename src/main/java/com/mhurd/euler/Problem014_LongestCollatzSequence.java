package com.mhurd.euler;

import com.mhurd.euler.helpers.CollatzSequence;
import com.mhurd.euler.helpers.EulerSolution;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

/**
 * The following iterative sequence is defined for the set of positive integers:
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting
 * numbers finish at 1. Which starting number, under one million, produces the longest chain?
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 * <p>
 * https://projecteuler.net/problem=14
 */
class Problem014_LongestCollatzSequence implements EulerSolution {

    // can't figure a way to do this with reductions (without creating lots of objects to carry the two pieces of
    // state through the reduction.
    private long largestCollatzChainUnder(long highestStart) {
        AtomicLong maxLength = new AtomicLong(0);
        AtomicLong maxStart = new AtomicLong(0);
        LongStream.range(1, highestStart).forEach(
                start -> {
                    Iterator<Long> itr = CollatzSequence.stream(start).iterator();
                    itr.next(); // always skip the first as it may be a 1
                    long length = 1;
                    while (itr.hasNext()) {
                        long cNumber = itr.next();
                        length++;
                        if (cNumber == 1) {
                            break;
                        }
                    }
                    if (maxLength.get() < length) {
                        maxLength.set(length);
                        maxStart.set(start);
                    }
                }
        );
        return maxStart.get();
    }

    public void solve() {
        assertEquals(837799L, largestCollatzChainUnder(1000000L));
    }

}
