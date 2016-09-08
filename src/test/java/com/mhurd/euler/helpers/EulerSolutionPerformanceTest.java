package com.mhurd.euler.helpers;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EulerSolutionPerformanceTest {

    @Test
    public void test() {
        final int iterations = 10;
        final AtomicInteger count = new AtomicInteger(0);
        long results = EulerSolutionPerformance.avgTimeInNanos(count::incrementAndGet, iterations);
        assertEquals(iterations, count.get());
        assertTrue(results > 0L);
    }

}
