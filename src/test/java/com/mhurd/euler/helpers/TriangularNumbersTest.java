package com.mhurd.euler.helpers;

import org.junit.Test;

import java.util.Iterator;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

public class TriangularNumbersTest {

    @Test
    public void test() {
        LongStream primes = TriangularNumbers.stream();
        Iterator<Long> itr = primes.iterator();
        assertEquals(Long.valueOf(1), itr.next());
        assertEquals(Long.valueOf(3), itr.next());
        assertEquals(Long.valueOf(6), itr.next());
        assertEquals(Long.valueOf(10), itr.next());
        assertEquals(Long.valueOf(15), itr.next());
        assertEquals(Long.valueOf(21), itr.next());
        assertEquals(Long.valueOf(28), itr.next());
        assertEquals(Long.valueOf(36), itr.next());
        assertEquals(Long.valueOf(45), itr.next());
        assertEquals(Long.valueOf(55), itr.next());
    }

}
